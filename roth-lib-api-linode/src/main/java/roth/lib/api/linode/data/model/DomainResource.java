package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class DomainResource implements Serializable
{
	@Property(name = "DOMAINID")
	protected Integer domainId;
	
	@Property(name = "RESOURCEID")
	protected Integer resourceId;
	
	@Property(name = "TYPE")
	protected String type;
	
	@Property(name = "NAME")
	protected String name;
	
	@Property(name = "TARGET")
	protected String target;
	
	@Property(name = "PRIORITY")
	protected Integer priority;
	
	@Property(name = "WEIGHT")
	protected Integer weight;
	
	@Property(name = "PORT")
	protected Integer port;
	
	@Property(name = "PROTOCOL")
	protected String protocol;
	
	@Property(name = "TTL_SEC")
	protected Integer ttlSec;
	
	public DomainResource()
	{
		
	}
	
	public Integer getDomainId()
	{
		return domainId;
	}
	
	public Integer getResourceId()
	{
		return resourceId;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	public Integer getPriority()
	{
		return priority;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public String getProtocol()
	{
		return protocol;
	}
	
	public Integer getTtlSec()
	{
		return ttlSec;
	}
	
	public DomainResource setDomainId(Integer domainId)
	{
		this.domainId = domainId;
		return this;
	}
	
	public DomainResource setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
		return this;
	}
	
	public DomainResource setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DomainResource setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DomainResource setTarget(String target)
	{
		this.target = target;
		return this;
	}
	
	public DomainResource setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public DomainResource setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
	public DomainResource setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public DomainResource setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public DomainResource setTtlSec(Integer ttlSec)
	{
		this.ttlSec = ttlSec;
		return this;
	}
	
}
