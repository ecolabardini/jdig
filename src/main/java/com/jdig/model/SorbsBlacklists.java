package com.jdig.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Enumeration of all services provided by SORBS. 
 * @see <a href="http://www.sorbs.net/using.shtml">SORBS site</a>
 */
public enum SorbsBlacklists {

    DNSBL(
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

    HTTP_DNSBL(
            "http.dnsbl.sorbs.net",
            "List of Open HTTP Proxy Servers.",
            "127.0.0.2"
    ),

    SOCKS_DNSBL(
            "socks.dnsbl.sorbs.net",
            "List of Open SOCKS Proxy Servers.",
            "127.0.0.3"
    ),

    MISC_DNSBL(
            "misc.dnsbl.sorbs.net",
            "List of open Proxy Servers not listed in the SOCKS or HTTP lists.",
            "127.0.0.4"
    ),

    SMTP_DNSBL(
            "smtp.dnsbl.sorbs.net",
            "List of Open SMTP relay servers.",
            "127.0.0.5"
    ),

    WEB_DNSBL(
            "web.dnsbl.sorbs.net",
            "List of web (WWW) servers which have spammer abusable vulnerabilities (e.g. FormMail scripts) Note: This zone now includes non-webserver IP addresses that have abusable vulnerabilities.",
            "127.0.0.7"
    ),

    NEW_SPAM(
            "new.spam.dnsbl.sorbs.net",
            "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last 48 hours.",
            "127.0.0.6"
    ),

    RECENT_SPAM(
            "recent.spam.dnsbl.sorbs.net",
            "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last 28 days (includes new.spam.dnsbl.sorbs.net).",
            "127.0.0.6"
    ),

    OLD_SPAM(
            "old.spam.dnsbl.sorbs.net",
            "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS within the last year. (includes recent.spam.dnsbl.sorbs.net).",
            "127.0.0.6"
    ),

    SPAM_DNSBL(
            "spam.dnsbl.sorbs.net",
            "List of hosts that have been noted as sending spam/UCE/UBE to the admins of SORBS at any time",
            "127.0.0. and not subsequently resolving the matter and/or requesting a delisting. (Includes both old.spam.dnsbl.sorbs.net and escalations.dnsbl.sorbs.net).",
            "127.0.0.6"
    ),

    ESCALATIONS_DNSBL(
            "escalations.dnsbl.sorbs.net",
            "This zone contains netblocks of spam supporting service providers",
            "127.0.0. including those who provide websites",
            "127.0.0. DNS or drop boxes for a spammer.  Spam supporters are added on a 'third strike and you are out' basis",
            "127.0.0. where the third spam will cause the supporter to be added to the list.",
            "127.0.0.6"
    ),

    BLOCK_DNSBL(
            "block.dnsbl.sorbs.net",
            "List of hosts demanding that they never be tested by SORBS.",
            "127.0.0.8"
    ),

    ZOMBIE_DNSBL(
            "zombie.dnsbl.sorbs.net",
            "List of networks hijacked from their original owners",
            "127.0.0. some of which have already used for spamming.",
            "127.0.0.9"
    ),

    DUL_DNSBL(
            "dul.dnsbl.sorbs.net",
            "Dynamic IP Address ranges (NOT a Dial Up list!)",
            "127.0.0.10"
    ),

    NOSERVER_DNSBL(
            "noserver.dnsbl.sorbs.net",
            "IP addresses and Netblocks of where system administrators and ISPs owning the network have indicated that servers should not be present.",
            "127.0.0.14"
    ),

    VIRUS_DNSBL(
            "virus.dnsbl.sorbs.net",
            "Hosts that have delivered known viruses to the SORBS spamtrap servers in the last 7 days.  The zone has a high overlap with the spam.dnsbl.sorbs.net as viruses that are not instantly recognised initially listed as spam (polymorphic viruses tend to do this.)",
            "127.0.0.15"
    ),

    RHSBL(
            "rhsbl.sorbs.net",
            "Aggregate zone (contains all RHS zones)",
            "127.0.0.11",
            "127.0.0.12"
    ),

    BADCONF_RHSBL(
            "badconf.rhsbl.sorbs.net",
            "List of domain names where the A or MX records point to bad address space.",
            "127.0.0.11"
    ),

    NOMAIL_RHSBL(
            "nomail.rhsbl.sorbs.net",
            "List of domain names where the owners have indicated no email should ever originate from these domains.",
            "127.0.0.12"
    ),

    SAFE_DNSBL(
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

    PROBLEMS_DNSBL(
            "problems.dnsbl.sorbs.net",
            "Includes http, socks, misc, smtp, new.spam, recent.spam, old.spam, escalations, web, block, zombie zones",
            "127.0.0.2",
            "127.0.0.5",
            "127.0.0.6",
            "127.0.0.7",
            "127.0.0.8",
            "127.0.0.9"
    ),

    PREEMPT_DNSBL(
            "preempt.dnsbl.sorbs.net",
            "Includes dul, noserver zones",
            "127.0.0.10",
            "127.0.0.14"
    ),

    RELAYS_DNSBL(
            "relays.dnsbl.sorbs.net",
            "Includes http, socks, misc, smtp zones",
            "127.0.0.2",
            "127.0.0.3",
            "127.0.0.4",
            "127.0.0.5"
    ),

    PROXIES_DNSBL(
            "proxies.dnsbl.sorbs.net",
            "Includes http, socks, misc zones",
            "127.0.0.2",
            "127.0.0.3",
            "127.0.0.4"
    ),

    L1_SPEWS(
            "l1.spews.dnsbl.sorbs.net",
            "SPEWS Level one listings",
            "127.0.0.2"
    ),

    L2_SPEWS(
            "l2.spews.dnsbl.sorbs.net",
            "SPEWS Level two listings",
            "127.0.0.2"
    ),

    SPEWS_DNSBL(
            "spews.dnsbl.sorbs.net",
            "ASPEWS Listings (includes level 1 and level 2)",
            "127.0.0.2"
    );


    private final String sorbsHostname;
    private final String description;
    private final String[] resultCode;
    private final Set<String> resultCodeSet = new HashSet<String>();

    /**
     * Creates a SORBS blacklist enum.
     *
     * @param sorbsHostname  hostname
     * @param description    description
     * @param resultCode     one or more result codes
     */
    SorbsBlacklists(
            String sorbsHostname, String description, String... resultCode) {
        this.sorbsHostname = sorbsHostname;
        this.description = description;
        this.resultCode = resultCode;
        if (resultCode != null) {
            Collections.addAll(resultCodeSet, resultCode);
        }
    }

    /**
     * @param ips  one or more IP addresses returned on a lookup to the blacklist
     *             service
     * @return     {@code true} if the codes are found on any of the IP addresses
     *             provided appears on the result codes defined by the
     *             blacklist.
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

}
