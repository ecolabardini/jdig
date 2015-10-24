package com.jdig.model;

public class DnsEntry {

	protected Type type;
	protected String name;
	private Integer priority;
	private Integer weight;
	private Integer port;

	public DnsEntry(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public DnsEntry(Type type, String name, Integer priority) {
		super();
		this.type = type;
		this.name = name;
		this.priority = priority;
	}

	public DnsEntry(Type type, String name, Integer priority, Integer weight, Integer port) {
		this.type = type;
		this.name = name;
		this.priority = priority;
		this.weight = weight;
		this.port = port;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Used by MX and SRV DNS entries
	 * @return {@link Integer}
	 */
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Used by SRV DNS entries
	 * @return {@link Integer}
	 */
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * Used by SRV DNS entries
	 * @return {@link Integer}
	 */
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "DnsEntry [" + ("type=" + type + ", ") + ("name=" + name)
				+ (priority != null ? ", priority=" + priority : "") + (weight != null ? ", weight=" + weight : "")
				+ (port != null ? ", port=" + port : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DnsEntry other = (DnsEntry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (type != other.type)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

}