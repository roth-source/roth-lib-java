package roth.lib.api.linode.data.request;

import roth.lib.api.linode.data.type.DomainResourceType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class DomainResourceRequest extends DomainIdRequest
{
	@Property(name = "Type")
	protected String type;
	
	@Property(name = "Name")
	protected String name;
	
	@Property(name = "Target")
	protected String target;
	
	@Property(name = "Priority")
	protected Integer priority;
	
	@Property(name = "Weight")
	protected Integer weight;
	
	@Property(name = "Port")
	protected Integer port;
	
	@Property(name = "Protocol")
	protected String protocol;
	
	@Property(name = "TTL_sec")
	protected Integer ttlSec;
	
	public DomainResourceRequest()
	{
		
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
	
	public DomainResourceRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DomainResourceRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DomainResourceRequest setTarget(String target)
	{
		this.target = target;
		return this;
	}
	
	public DomainResourceRequest setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public DomainResourceRequest setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
	public DomainResourceRequest setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public DomainResourceRequest setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public DomainResourceRequest setTtlSec(Integer ttlSec)
	{
		this.ttlSec = ttlSec;
		return this;
	}
	
	public DomainResourceRequest setType(DomainResourceType domainResourceType)
	{
		this.type = domainResourceType.get();
		return this;
	}
	
}
