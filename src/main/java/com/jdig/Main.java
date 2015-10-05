package com.jdig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
    public static void main(String args[]) {
    	List<DnsEntry> entries = new DnsLookup().lookup("8.8.8.8", "github.com", Type.all());
    	for (DnsEntry de : entries) logger.debug(de.toString());
    }
}
