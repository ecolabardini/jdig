package com.jdig.parser;

import org.junit.Assert;
import org.junit.Test;

public class DnsParserTest {

	@Test
	public void testReverseIpAddress() {
		Assert.assertEquals("1.0.0.127", DnsParser.getReverseIpAddress("127.0.0.1"));
	}
	
	@Test
	public void reverseIpShouldBeNull() {
		Assert.assertNull(DnsParser.getReverseIpAddress("127.0"));
		Assert.assertNull(DnsParser.getReverseIpAddress("127.0.0.0.1"));
		Assert.assertNull(DnsParser.getReverseIpAddress(""));
	}
	
}
