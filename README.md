# JDIG - A simple Java library for DNS queries

JDIG is a simple Java library that can be added to your project in order to provide an easy way to do DNS queries. 

It also support DNS-based Blackhole List (DNSBL) / Real-time Blackhole List (RBL) queries. [What is this?](https://en.wikipedia.org/wiki/DNSBL)

It is built with:
 - Java 7
 - Maven
 - SLF4J
 - JUnit

### Current version

    0.0.1
    
### How to add to my project

    http://mvnrepository.com/artifact/com.github.ecolabardini/jdig/0.0.1

### Suported DNS queries

    A, NS, MX, SRV, TXT, CNAME or Type.any()

### Examples

The DnsService object can be constructed with several arguments, please see the examples below or check the javadoc for full explanation.

#### Example - Simple DNS query

```java
import java.util.List;
import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.service.DnsService;

(...)

// Simple DnsService
List<DnsEntry> entries = new DnsService().lookup("google.com", Type.any());

```

#### Example - Query at specific DNS Server

```java
(...)

// Query at Google DNS Servers - 8.8.8.8
// The port can also be provided, e.g.: 8.8.8.8:53
List<DnsEntry> entries = new DnsService("8.8.8.8").lookup("google.com", Type.MX);

```

#### Example - Specific properties for InitialDirContext 

JDIG uses the underlying JNDI directory service so you can provide your own environment properties, e.g.:

```java
import java.util.List;
import java.util.Properties;

import javax.naming.Context;

import com.jdig.model.DnsEntry;
import com.jdig.model.Type;
import com.jdig.service.DnsService;

(...)

Properties environment = new Properties();
environment.put(Context.PROVIDER_URL, "dns://8.8.8.8:53");
environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");

List<DnsEntry> entries = new DnsService(environment).lookup("google.com", Type.MX);

```
    
#### Example - DNSBL query

This queries are provided by the ``blackListLookup()`` method. Note that the DNSBL list is a mandatory argument. 

For DNSBL lists, [click here](https://en.wikipedia.org/wiki/Comparison_of_DNS_blacklists).

In the example below, we are checking if the IP address 1.2.3.4 is in the [SORBS](http://www.sorbs.net/) list. 

```java
(...)

List<DnsEntry> blacklistLookup = new DnsService().blacklistLookup("1.2.3.4", "dnsbl.sorbs.net");
```

To understand SORBS return codes, check [Using SORBS](http://www.sorbs.net/using.shtml).

Note that each list has its own return codes so you should check their webpages.


#### Contributing

Please contact me if you have any ideas, suggestions or, even better, you want to collaborate on this project!


#### License

This library is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
