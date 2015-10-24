package com.jdig.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.parser.DnsParser;

public class DnsService {
	
	private final Logger logger = LoggerFactory.getLogger(DnsService.class);
	
	private String[] dnsProviders;
	private Long timeout;
	private Long retries;
	private Boolean authoritative;
	private Boolean recursion;

	private Properties environment;
	
	public DnsService() { }
	
	/**
	 * Constructs a DnsService with the specified properties. 
	 * For instance, you can use the following environment properties: Context.AUTHORITATIVE, Context.PROVIDER_URL etc.
	 * 
	 * @param environment used to create the initial DirContext
	 */
	public DnsService(Properties environment) {
		this.environment = environment;
	}

	/***
 	 * Constructs a DnsService with the specified parameters 
 	 * 
	 * @param dnsProviders Specifies the host name and port of the DNS server(s), e.g.: 8.8.8.8:53 
	 * @param timeout Specifies the number of milliseconds to use as the initial timeout period using the exponential backoff algorithm. If this property has not been set, the default initial timeout is 1000 milliseconds.
	 * @param retries Specifies the number of times to retry each server using the exponential backoff algorithm. If this property has not been set, the default number of retries is 4.
	 * @param authoritative  If its value is "true", only authoritative responses are accepted from DNS servers; otherwise, all responses are accepted.
	 * @param recursion This property is used to specify that recursion is allowed on DNS queries. 
	 */
	public DnsService(String[] dnsProviders, Long timeout, Long retries, Boolean authoritative, Boolean recursion) {
		this.dnsProviders = dnsProviders;
		this.timeout = timeout;
		this.retries = retries;
		this.authoritative = authoritative;
	}
	
	/***
 	 * Constructs a DnsService with the specified parameters. 
 	 * 
	 * @param dnsProvider the host name and port of the DNS server, e.g.: 8.8.8.8:53 
	 * @param timeout the number of milliseconds to use as the initial timeout period using the exponential backoff algorithm. If this property has not been set, the default initial timeout is 1000 milliseconds.
	 * @param retries the number of times to retry each server using the exponential backoff algorithm. If this property has not been set, the default number of retries is 4.
	 * @param authoritative  if true only authoritative responses are accepted from DNS servers; otherwise, all responses are accepted.
	 * @param recursion if true recursion is allowed on DNS queries. 
	 */
	public DnsService(String dnsProvider, Long timeout, Long retries, Boolean authoritative, Boolean recursion) {
		this.dnsProviders = dnsProviders != null ? new String[] { dnsProvider } : null;
		this.timeout = timeout;
		this.retries = retries;
		this.authoritative = authoritative;
	}

	/**
 	 * Constructs a DnsService with the specified provider.
 	 *  
	 * @param dnsProvider is the host name and port of the DNS server, e.g.: 8.8.8.8:53
	 */
	public DnsService(String dnsProvider) {
		dnsProviders = dnsProviders != null ? new String[] { dnsProvider } : null;
	}

	private List<DnsEntry> defaultLookup(InitialDirContext context, String url, Type... types) throws NamingException {
		try {
			Set<String> attr = new HashSet<String>();
			for (Type t : types) attr.add(t.toString());
			Attributes attributes = context.getAttributes(url, attr.toArray(new String[attr.size()]));
			return DnsParser.parse(attributes, types);
		} catch (NamingException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Returns a newly InitialDirContext every time, since JNDI specifies 
	 * that concurrent access to the same Context instance is not 
	 * guaranteed to be thread-safe
	 * 
	 * @return {@link InitialDirContext}
	 * @throws NamingException if a naming exception is encountered
	 */
	private InitialDirContext getInitialDirContext() throws NamingException {
		try {
			if (this.environment != null) {
				return new InitialDirContext(environment);
			}
			
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
			if (authoritative != null) env.put(Context.AUTHORITATIVE, String.valueOf(authoritative));
			if (recursion != null) env.put("com.sun.jndi.dns.recursion", String.valueOf(this.recursion));
			if (timeout != null) env.put("com.sun.jndi.dns.timeout.initial", String.valueOf(this.timeout));
			if (retries != null) env.put("com.sun.jndi.dns.timeout.retries", String.valueOf(this.retries));
			
			if (dnsProviders != null && dnsProviders.length > 0) {
				StringBuilder providerUrl = new StringBuilder();
				for (int i=0; i < dnsProviders.length; i++) {
					providerUrl.append("dns://" + dnsProviders[i]);
					if (i>0) providerUrl.append(" ");
				}
				env.put(Context.PROVIDER_URL, providerUrl.toString());
			}
			
			return new InitialDirContext(env);
		} catch (NamingException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Do a resource lookup of the specified type(s)
	 * 
	 * @param domain the name of the resource record that is to be looked up
	 * @param type indicates what type of query is required — ANY, A, NS, MX, SPF, SRV, TXT or CNAME
	 * @return {@link List} of {@link DnsEntry}
	 * @throws NamingException if a naming exception is encountered
	 */
	
	public List<DnsEntry> lookup(String domain, Type... type) throws NamingException {
		return defaultLookup(getInitialDirContext(), domain, type);
	}
	
	/**
	 * Check if the IP address is blacklisted by any of the lists
	 * 
	 * @param ipAddress the ipAddress to be looked up
	 * @param lists DNSBL lists, e.g. bl.spamcop.net, dnsbl.sorbs.net
	 * @return {@link List} of {@link DnsEntry}
	 * @throws NamingException if a naming exception is encountered
	 */
	public List<DnsEntry> blacklistLookup(String ipAddress, List<String> lists) throws NamingException {
		return blacklistLookup(ipAddress, lists.toArray(new String[lists.size()]));
	}
	
	/**
	 * Check if the IP address is blacklisted by any of the lists
	 * 
	 * @param ipAddress the ipAddress which to be looked up
	 * @param lists DNSBL lists, e.g. bl.spamcop.net, dnsbl.sorbs.net
	 * @return {@link List} of {@link DnsEntry}
	 * @throws NamingException if a naming exception is encountered
	 */
	public List<DnsEntry> blacklistLookup(String ipAddress, String ... lists) throws NamingException {
		List<DnsEntry> entries = new ArrayList<DnsEntry>();
		for (String list : lists) {
			String query = DnsParser.getReverseIpAddress(ipAddress) + "." + list;
			entries.addAll(defaultLookup(getInitialDirContext(), query, Type.any()));
		}
		return entries;
	}

}

