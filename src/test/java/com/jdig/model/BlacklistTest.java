package com.jdig.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.jdig.model.Blacklist.*;

public class BlacklistTest {

    @Test
    public void testBlacklisted()
    {
        // test with a blacklist that has a single result code
        assertFalse(SORBS_NOMAIL_RHSBL.isBlacklisted("192.168.1.1"));
        assertFalse(SORBS_NOMAIL_RHSBL.isBlacklisted("192.168.1.1", "172.16.0.2"));
        assertTrue(SORBS_NOMAIL_RHSBL.isBlacklisted("127.0.0.12"));
        assertTrue(SORBS_NOMAIL_RHSBL.isBlacklisted("192.168.1.1","127.0.0.12"));

        // test with a blacklist that has multiple result codes
        assertFalse(SORBS_SAFE_DNSBL.isBlacklisted("192.168.1.1"));
        assertFalse(SORBS_SAFE_DNSBL.isBlacklisted("192.168.1.1", "172.16.0.2"));
        assertTrue(SORBS_SAFE_DNSBL.isBlacklisted("127.0.0.2"));
        assertTrue(SORBS_SAFE_DNSBL.isBlacklisted("192.168.1.1","127.0.0.5"));

    }
}
