package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.type.DomainRecordType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainRecordRequest implements Serializable
{
	@Property(name = "type")
	protected String type;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "data")
	protected String data;
	
	@Property(name = "priority")
	protected Integer priority;
	
	@Property(name = "port")
	protected Integer port;
	
	@Property(name = "weight")
	protected Integer weight;
	
	public DomainRecordRequest(DomainRecordType domainRecordType)
	{
		this.type = domainRecordType.get();
	}
	
	public DomainRecordRequest(DomainRecordType domainRecordType, String name, String data)
	{
		this(domainRecordType);
		this.name = name;
		this.data = data;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getData()
	{
		return data;
	}
	
	public Integer getPriority()
	{
		return priority;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public DomainRecordRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DomainRecordRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DomainRecordRequest setData(String data)
	{
		this.data = data;
		return this;
	}
	
	public DomainRecordRequest setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public DomainRecordRequest setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public DomainRecordRequest setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
}
