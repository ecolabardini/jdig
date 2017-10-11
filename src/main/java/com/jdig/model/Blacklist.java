package com.jdig.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Enumeration of all services provided by SORBS. 
 * @see <a href="http://www.sorbs.net/using.shtml">SORBS site</a>
 */
public enum Blacklist {

        SORBS_DNSBL(
                "dnsbl.sorbs.net",
                "Aggregate zone (contains all the following DNS zones except spam.dnsbl.sorbs.net)",
                "127.0.0.2",
                "127.0.0.3",
                "127.0.0.4",
                "127.0.0.5",
                "127.0.0.6",
                "127.0.0.7",
                "127.0.0.8",
                "127.0.0.9",
                "127.0.0.10",
                "127.0.0.11",
                "127.0.0.12",
                "127.0.0.13",
                "127.0.0.14",
                "127.0.0.15"
           ),
       
           SORBS_HTTP_DNSBL(
                "http.dnsbl.sorbs.net",
                "List of Open HTTP Proxy Servers.",
                "127.0.0.2"
           ),
       
           SORBS_SOCKS_DNSBL(
                "socks.dnsbl.sorbs.net",
                "List of Open SOCKS Proxy Servers.",
                "127.0.0.3"
           ),
       
           SORBS_MISC_DNSBL(
                "misc.dnsbl.sorbs.net",
                "List of open Proxy Servers not listed in the SOCKS or HTTP lists.",
                "127.0.0.4"
           ),
       
           SORBS_SMTP_DNSBL(
                "smtp.dnsbl.sorbs.net",
                "List of Open SMTP relay servers.",
                "127.0.0.5"
           ),
       
           SORBS_WEB_DNSBL(
                "web.dnsbl.sorbs.net",
                "List of web (WWW) servers which have spammer abusable vulnerabilities (e.g. FormMail scripts) Note: This zone now includes non-webserver IP addresses that have abusable vulnerabilities.",
                "127.0.0.7"
           ),
       
           SORBS_NEW_SPAM(
                "new.spam.dnsbl.sorbs.net",
                "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last 48 hours.",
                "127.0.0.6"
           ),
       
           SORBS_RECENT_SPAM(
                "recent.spam.dnsbl.sorbs.net",
                "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last 28 days (includes new.spam.dnsbl.sorbs.net).",
                "127.0.0.6"
           ),
       
           SORBS_OLD_SPAM(
                "old.spam.dnsbl.sorbs.net",
                "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last year. (includes recent.spam.dnsbl.sorbs.net).",
                "127.0.0.6"
           ),
       
           SORBS_SPAM_DNSBL(
                "spam.dnsbl.sorbs.net",
                "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS at any time; and not subsequently resolving the matter and/or requesting a delisting. (Includes both old.spam.dnsbl.sorbs.net and escalations.dnsbl.sorbs.net).",
                "127.0.0.6"
           ),
       
           SORBS_ESCALATIONS_DNSBL(
                "escalations.dnsbl.sorbs.net",
                "This zone contains netblocks of spam supporting service providers; including those who provide websites; DNS or drop boxes for a spammer.  Spam supporters are added on a 'third strike and you are out' basis; where the third spam will cause the supporter to be added to the list.",
                "127.0.0.6"
           ),
       
           SORBS_BLOCK_DNSBL(
                "block.dnsbl.sorbs.net",
                "List of hosts demanding that they never be tested by SORBS.",
                "127.0.0.8"
           ),
       
           SORBS_ZOMBIE_DNSBL(
                "zombie.dnsbl.sorbs.net",
                "List of networks hijacked from their original owners; some of which have already used for spamming.",
                "127.0.0.9"
           ),
       
           SORBS_DUL_DNSBL(
                "dul.dnsbl.sorbs.net",
                "Dynamic IP Address ranges (NOT a Dial Up list!)",
                "127.0.0.10"
           ),
       
           SORBS_NOSERVER_DNSBL(
                "noserver.dnsbl.sorbs.net",
                "IP addresses and Netblocks of where system administrators and ISPs owning the network have indicated that servers should not be present.",
                "127.0.0.14"
           ),
       
           SORBS_VIRUS_DNSBL(
                "virus.dnsbl.sorbs.net",
                "Hosts that have delivered known viruses to the SORBS spamtrap servers in the last 7 days.  The zone has a high overlap with the spam.dnsbl.sorbs.net as viruses that are not instantly recognised initially listed as spam (polymorphic viruses tend to do this.)",
                "127.0.0.15"
           ),
       
           SORBS_RHSBL(
                "rhsbl.sorbs.net",
                "Aggregate zone (contains all RHS zones)",
                "127.0.0.11",
                "127.0.0.12"
           ),
       
           SORBS_BADCONF_RHSBL(
                "badconf.rhsbl.sorbs.net",
                "List of domain names where the A or MX records point to bad address space.",
                "127.0.0.11"
           ),
       
           SORBS_NOMAIL_RHSBL(
                "nomail.rhsbl.sorbs.net",
                "List of domain names where the owners have indicated no email should ever originate from these domains.",
                "127.0.0.12"
           ),
       
           SORBS_SAFE_DNSBL(
                "safe.dnsbl.sorbs.net",
                "Includes http, socks, misc, smtp, new.spam, web, block, zombie, dul, noserver zones",
                "127.0.0.2",
                "127.0.0.5",
                "127.0.0.6",
                "127.0.0.7",
                "127.0.0.8",
                "127.0.0.9",
                "127.0.0.10",
                "127.0.0.14"
           ),
       
           SORBS_PROBLEMS_DNSBL(
                "problems.dnsbl.sorbs.net",
                "Includes http, socks, misc, smtp, new.spam, recent.spam, old.spam, escalations, web, block, zombie zones",
                "127.0.0.2",
                "127.0.0.5",
                "127.0.0.6",
                "127.0.0.7",
                "127.0.0.8",
                "127.0.0.9"
           ),
       
           SORBS_PREEMPT_DNSBL(
                "preempt.dnsbl.sorbs.net",
                "Includes dul, noserver zones",
                "127.0.0.10",
                "127.0.0.14"
           ),
       
           SORBS_RELAYS_DNSBL(
                "relays.dnsbl.sorbs.net",
                "Includes http, socks, misc, smtp zones",
                "127.0.0.2",
                "127.0.0.3",
                "127.0.0.4",
                "127.0.0.5"
           ),
       
           SORBS_PROXIES_DNSBL(
                "proxies.dnsbl.sorbs.net",
                "Includes http, socks, misc zones",
                "127.0.0.2",
                "127.0.0.3",
                "127.0.0.4"
           ),
       
           SORBS_L1_SPEWS(
                "l1.spews.dnsbl.sorbs.net",
                "SPEWS Level one listings",
                "127.0.0.2"
           ),
       
           SORBS_L2_SPEWS(
                "l2.spews.dnsbl.sorbs.net",
                "SPEWS Level two listings",
                "127.0.0.2"
           ),
       
           SORBS_SPEWS_DNSBL(
                "spews.dnsbl.sorbs.net",
                "ASPEWS Listings",
                "127.0.0.2"
           );

    private static final Map<String,Blacklist> hostnameMap =
            new HashMap<String,Blacklist>();
    static {
            for (Blacklist list : Blacklist.values()) {
                hostnameMap.put(list.hostname, list);
            }
    }
    
    private final String hostname;
    private final String description;
    private final String[] resultCode;
    private final Set<String> resultCodeSet = new HashSet<String>();

    /**
     * Creates a blacklist enum.
     *
     * @param hostname       hostname of the DNS based blacklist lookup service
     * @param description    description
     * @param resultCode     one or more result codes
     */
    Blacklist(
            String hostname, String description, String... resultCode) {
        this.hostname = hostname;
        this.description = description;
        this.resultCode = resultCode;
        if (resultCode != null) {
            Collections.addAll(resultCodeSet, resultCode);
        }
    }

    /**
     * @param ips  one or more IP addresses returned on a lookup to the blacklist
     *             service
     * @return     {@code true} if any of the provided IP addresses are registered
     *             in the blacklist.
     */
    public boolean isBlacklisted(String ... ips) {
        if (ips != null) {
            for (String code : ips) {
                if (resultCodeSet.contains(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param hostname  host name of the DNS blacklist service
     * @param ips       IP addresses returned by the blacklist service
     * @return          {@code true} if the any of the provided IP addresses
     *                  are registered in the blacklist.
     */
    public static boolean isBlacklisted(String hostname, List<DnsEntry> ips) {
        if (ips != null && hostname != null) {
            List<String> codes = new ArrayList<String>();
            for (DnsEntry ip : ips) {
                codes.add(ip.getName());
            }

            Blacklist blacklist = hostnameMap.get(hostname);
            if (blacklist != null) {
                return blacklist.isBlacklisted(codes.toArray(new String[codes.size()]));
            }
        }
        return false;
    }

}
