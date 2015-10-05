package com.jdig.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class DnsEntry {

	@NonNull
	protected Type type;
	
	@NonNull
	protected String name;
	
	private Integer priority;
	private Integer weight;
	private Integer port;
	
	@Override
	public String toString() {
		return "DnsEntry [" + ("type=" + type + ", ") + ("name=" + name)
				+ (priority != null ? ", priority=" + priority : "")
				+ (weight != null ? ", weight=" + weight : "") + (port != null ? ", port=" + port : "") + "]";
	}
	
}
