package com.jdig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.parser.DnsParser;

public class DnsLookup {
	
	private static final Logger logger = LoggerFactory.getLogger(DnsLookup.class);
	private	InitialDirContext iDirC;

	public DnsLookup() {
		try {
			iDirC = new InitialDirContext();
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
	}
	
	public DnsLookup(Properties environment) {
		try {
			iDirC = new InitialDirContext(environment);
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
	}
	
	private List<DnsEntry> defaultLookup(String url, Type... types) {
		try {
			Set<String> attr = new HashSet<String>();
			for (Type t : types) attr.add(t.toString());
			Attributes attributes = iDirC.getAttributes(url, attr.toArray(new String[attr.size()]));
			return DnsParser.parse(attributes, types);
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		
		return new ArrayList<DnsEntry>();
	}
	
	public List<DnsEntry> lookup(String dnsServer, String domain, Type... type) {
		logger.debug("Digging at " + dnsServer + " for " + domain);
		return defaultLookup("dns://"+dnsServer+"/"+domain, type);
	}
	
	public List<DnsEntry> lookup(String domain, Type... type) {
		return defaultLookup("dns:/"+domain, type);
	}

}

