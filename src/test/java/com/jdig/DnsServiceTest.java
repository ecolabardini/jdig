package com.jdig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;

public class DnsServiceTest {
	
	public static Logger logger = LoggerFactory.getLogger(DnsServiceTest.class);
	
	@Test
	public void shouldDigGoogleMXAtGoogleDnsServer() throws NamingException {
        List<DnsEntry> lookupEntries = new DnsService("8.8.8.8:53").lookup("google.com", Type.MX);
        
        List<DnsEntry> expectedEntries = new ArrayList<DnsEntry>();
        expectedEntries.add(new DnsEntry(Type.MX, "alt3.aspmx.l.google.com.", 40));
        expectedEntries.add(new DnsEntry(Type.MX, "alt1.aspmx.l.google.com.", 20));
        expectedEntries.add(new DnsEntry(Type.MX, "aspmx.l.google.com.", 10));
        expectedEntries.add(new DnsEntry(Type.MX, "alt4.aspmx.l.google.com.", 50));
        expectedEntries.add(new DnsEntry(Type.MX, "alt2.aspmx.l.google.com.", 30));

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
	public void shouldDigAtGithubAuthoritativeServer() throws NamingException {
		List<DnsEntry> lookupEntries = new DnsService("ns1.p16.dynect.net.", null, null, true, null).lookup("github.com", Type.MX);
		assertTrue(lookupEntries.size() > 0);
	}

	@Test(expected=NamingException.class)
	public void shouldThrowExceptionWhenQueryingOnNonAuthoritativeServer() throws NamingException {
		new DnsService("8.8.8.8", null, null, true, null).lookup("google.com", Type.MX);
	}

}
