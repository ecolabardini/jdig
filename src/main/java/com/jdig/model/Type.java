package com.jdig.model;

public enum Type {
	A,
    NS,
    MX,
    SRV,
    TXT,
    CNAME;
    
    public static Type[] all() {
		return Type.values();
	}
}
