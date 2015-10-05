package com.jdig.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;

public class DnsParser {
	
	public static List<DnsEntry> parse(Attributes allAttributes, Type[] types) throws NamingException {
		List<DnsEntry> entries = new ArrayList<DnsEntry>();

		for (Type type : types) {
			Attribute attributes = allAttributes.get(type.toString());
	        if (attributes != null) {
	        	entries.addAll(parseAttributes(attributes, type));
	        }
		}
		
		return entries;
	}

	private static List<DnsEntry> parseAttributes(Attribute attributes, Type type) throws NamingException {
		List<DnsEntry> entries = new ArrayList<DnsEntry>();

		for (int i = 0; i < attributes.size(); i++) {
			Object object = attributes.get(i);
			if (object != null) {
				switch (type) {
					case MX: 
						entries.add(parseMXAttribute(object)); 
						break;
					case SRV: 
						entries.add(parseSRVAttribute(object));
						break;
					default: 
						entries.add(new DnsEntry(type, object.toString()));
				}	
			}
		}
		
		return entries;
	}
	
	private static DnsEntry parseMXAttribute(Object attribute) {
		StringTokenizer stk = new StringTokenizer(attribute.toString());
		Integer priority = Integer.valueOf(stk.nextToken());
		String name = stk.nextToken();
		return new DnsEntry(Type.MX, name, priority, null, null);
	}
	
	private static DnsEntry parseSRVAttribute(Object attribute) {
		StringTokenizer stk = new StringTokenizer(attribute.toString());
		Integer priority = Integer.valueOf(stk.nextToken());
		Integer weight = Integer.valueOf(stk.nextToken());
		Integer port = Integer.valueOf(stk.nextToken());
		String name = stk.nextToken();
		return new DnsEntry(Type.SRV, name, priority, weight, port);
	}

}
