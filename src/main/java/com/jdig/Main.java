package com.jdig;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.service.DnsService;

public class Main {

	public static void main(String args[]) {
		String dnsServer = null;
		String domain = null;
		Type[] types = null;
		
		String[] simpleArgs = preProcessArgs(args);
	    try {
			CommandLine line = new DefaultParser().parse( getOptions(), simpleArgs);

			if (line.hasOption("help")) {
				usage();
			}
			
			if (line.hasOption("type")) {
				String value = line.getOptionValue("type");
				if (value.equals("ANY")) types = Type.all();
				else {
					try { types = new Type[] { Type.valueOf(value) }; } 
					catch (Exception e) { throw new ParseException("Unrecognized option: --type=" + line.getOptionValue("type"));};
				}
			}
			
			if (line.hasOption("name")) {
				domain = line.getOptionValue("name");
			}
			
			if (line.hasOption("server")) {
				dnsServer = line.getOptionValue("server");
			}
			
			List<DnsEntry> entries = new DnsService(dnsServer).lookup(domain, types);
			for (DnsEntry entry : entries) {
				System.out.println(entry.toString());
			}
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			usage();
		} catch (NamingException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Process the alternative method to normal arguments
	 */
	private static String[] preProcessArgs(String[] args) {
		List<String> args2 = new ArrayList<String>();
		
		for (String arg : args) {
			if (arg.startsWith("@")) {
				args2.add("--server=" + arg.split("@")[1]);
			} else {
				Type queryType = null;
				try {queryType = Type.valueOf(arg);} catch(Exception e) {};
				if (queryType != null) {
					args2.add("--type="+arg);
					continue;
				}
				
				if (args2.size() == 2) {
					args2.add("--name=" + arg);
				}
			}
		}
		
		return args2.size() == 3 ? args2.toArray(new String[args2.size()]) : args;
	}

	public static Options getOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder().longOpt("type").hasArg().desc("indicates what type of query is required — ANY, A, NS, MX, SPF, SRV, TXT or CNAME. "
				+ "If no type argument is supplied, the lookup will be for an A record.").build());
		options.addOption(Option.builder().longOpt("server").hasArg().desc("is the name or IP address of the name server to query").build());
		options.addOption(Option.builder().longOpt("name").hasArg().desc("the name of the resource record that is to be looked up").build());
		options.addOption(Option.builder().longOpt("help").desc("display this help").build());
		
		return options;
	}
	
	public static void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "jdig", getOptions());
		System.out.println("\nAlternative method / simple invocation:"
				+ "\n\n    jdig @server type name");
	}
	
}
