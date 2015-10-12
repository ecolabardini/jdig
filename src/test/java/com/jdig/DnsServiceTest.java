package com.jdig;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;

public class DnsLookupTest {

        public static Logger logger = LoggerFactory.getLogger(DnsLookupTest.class);

        @Test
        public void shouldDigGoogleMXAtGoogleDnsServer() {
                List<DnsEntry> lookupEntries = new DnsLookup().lookup("8.8.8.8", "google.com", Type.MX);

                List<DnsEntry> expectedEntries = new ArrayList<>();
                expectedEntries.add(new DnsEntry(Type.MX, "alt3.aspmx.l.google.com.", 40));
                expectedEntries.add(new DnsEntry(Type.MX, "alt1.aspmx.l.google.com.", 20));
                expectedEntries.add(new DnsEntry(Type.MX, "aspmx.l.google.com.", 10));
                expectedEntries.add(new DnsEntry(Type.MX, "alt4.aspmx.l.google.com.", 50));
                expectedEntries.add(new DnsEntry(Type.MX, "alt2.aspmx.l.google.com.", 30));

                lookupEntries.sort((e1,e2) -> e1.getName().compareTo(e2.getName()));
                expectedEntries.sort((e1,e2) -> e1.getName().compareTo(e2.getName()));

                assertEquals(expectedEntries, lookupEntries);
        }

}
