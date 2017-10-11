package com.jdig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.service.DnsService;

public class DnsServiceTest {
	
	public static Logger logger = LoggerFactory.getLogger(DnsServiceTest.class);
	
	@Test
	public void shouldDigGoogleMXAtGoogleDnsServer() throws NamingException {
        List<DnsEntry> lookupEntries = new DnsService("8.8.8.8:53").lookup("google.com", Type.MX);
        List<DnsEntry> expectedEntries = expectedGoogleMxEntries();

        Comparator<DnsEntry> nameComparator = new Comparator<DnsEntry>() {
			@Override
			public int compare(DnsEntry o1, DnsEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
        };
        
        Collections.sort(lookupEntries, nameComparator);
        Collections.sort(expectedEntries, nameComparator);
        
        assertEquals(expectedEntries, lookupEntries);
	}
	
	@Test
	public void shouldDigGoogleMXAtGoogleDnsServiceWithPropertiesFile() throws NamingException {
		Properties environment = new Properties();
		environment.put(Context.PROVIDER_URL, "dns://8.8.8.8:53");
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");

		List<DnsEntry> lookupEntries = new DnsService(environment).lookup("google.com", Type.MX);
        List<DnsEntry> expectedEntries = expectedGoogleMxEntries();

        Comparator<DnsEntry> nameComparator = new Comparator<DnsEntry>() {
			@Override
			public int compare(DnsEntry o1, DnsEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
        };
        
        Collections.sort(lookupEntries, nameComparator);
        Collections.sort(expectedEntries, nameComparator);
        
        assertEquals(expectedEntries, lookupEntries);
	}
	
	private List<DnsEntry> expectedGoogleMxEntries() {
		List<DnsEntry> expectedEntries = new ArrayList<DnsEntry>();
        expectedEntries.add(new DnsEntry(Type.MX, "alt3.aspmx.l.google.com.", 40));
        expectedEntries.add(new DnsEntry(Type.MX, "alt1.aspmx.l.google.com.", 20));
        expectedEntries.add(new DnsEntry(Type.MX, "aspmx.l.google.com.", 10));
        expectedEntries.add(new DnsEntry(Type.MX, "alt4.aspmx.l.google.com.", 50));
        expectedEntries.add(new DnsEntry(Type.MX, "alt2.aspmx.l.google.com.", 30));
        return expectedEntries;
	}
	
	@Test(expected=NamingException.class)
	public void shouldThrowExceptionWhenQueryingOnNonAuthoritativeServer() throws NamingException {
		new DnsService("8.8.8.8", null, null, true, null).lookup("google.com", Type.MX);
	}
	
	@Test
	public void shouldDoBlacklistLookupByArray() throws NamingException {
		List<DnsEntry> blacklistLookup = new DnsService().blacklistLookup("127.0.0.2", "bl.spamcop.net", "dnsbl.sorbs.net");
		assertTrue(blacklistLookup.size() > 0);
	}

	@Test
	public void shouldDoBlacklistLookupByList() throws NamingException {
		List<String> myDnsBLlists = Arrays.asList("bl.spamcop.net", "dnsbl.sorbs.net");
		List<DnsEntry> blacklistLookup = new DnsService().blacklistLookup("127.0.0.2", myDnsBLlists);
		assertTrue(blacklistLookup.size() > 1);
	}

	@Test
	public void testBlacklistedLookupByArray() throws NamingException {
		assertTrue(new DnsService().isBlacklisted("127.0.0.2", "bl.spamcop.net", "dnsbl.sorbs.net"));
	}

	@Test
	public void testBlacklistedLookupByList() throws NamingException {
		List<String> myDnsBLlists = Arrays.asList("bl.spamcop.net", "dnsbl.sorbs.net");
		assertTrue(new DnsService().isBlacklisted("127.0.0.2", myDnsBLlists));
	}

}
