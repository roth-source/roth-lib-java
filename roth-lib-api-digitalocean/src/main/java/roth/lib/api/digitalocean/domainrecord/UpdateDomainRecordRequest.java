package roth.lib.api.digitalocean.domainrecord;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.type.DomainRecordType;

@Entity
@SuppressWarnings("serial")
public class UpdateDomainRecordRequest implements Serializable
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
	
	public UpdateDomainRecordRequest(DomainRecordType domainRecordType)
	{
		this.type = domainRecordType.get();
	}
	
	public UpdateDomainRecordRequest(DomainRecordType domainRecordType, String name, String data)
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
	
	public UpdateDomainRecordRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public UpdateDomainRecordRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public UpdateDomainRecordRequest setData(String data)
	{
		this.data = data;
		return this;
	}
	
	public UpdateDomainRecordRequest setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public UpdateDomainRecordRequest setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public UpdateDomainRecordRequest setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
}
