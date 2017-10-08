package com.jdig.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.jdig.model.SorbsBlacklists.*;

public class SorbsBlacklistsTest {

    @Test
    public void testBlacklisted()
    {
        // test with a blacklist that has a single result code
        assertFalse(NOMAIL_RHSBL.isBlacklisted("192.168.1.1"));
        assertFalse(NOMAIL_RHSBL.isBlacklisted("192.168.1.1", "172.16.0.2"));
        assertTrue(NOMAIL_RHSBL.isBlacklisted("127.0.0.12"));
        assertTrue(NOMAIL_RHSBL.isBlacklisted("192.168.1.1","127.0.0.12"));

        // test with a blacklist that has multiple result codes
        assertFalse(SAFE_DNSBL.isBlacklisted("192.168.1.1"));
        assertFalse(SAFE_DNSBL.isBlacklisted("192.168.1.1", "172.16.0.2"));
        assertTrue(SAFE_DNSBL.isBlacklisted("127.0.0.2"));
        assertTrue(SAFE_DNSBL.isBlacklisted("192.168.1.1","127.0.0.5"));

    }
}
