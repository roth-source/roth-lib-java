package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.ProtocolType;
import roth.lib.api.cloudflare.data.type.RecordType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class RecordRequest extends DomainRequest
{
	@Property(name = "type")
	protected String type;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "content")
	protected String content;
	
	@Property(name = "ttl")
	protected Integer ttl;
	
	@Property(name = "prio")
	protected Integer priority;
	
	@Property(name = "service")
	protected String service;
	
	@Property(name = "srvname")
	protected String srvname;
	
	@Property(name = "protocol")
	protected String protocol;
	
	@Property(name = "weight")
	protected Integer weight;
	
	@Property(name = "port")
	protected Integer port;
	
	@Property(name = "target")
	protected String target;
	
	protected RecordRequest(String domain)
	{
		super(domain);
		this.ttl = 1;
	}
	
	protected RecordRequest(RecordRequest recordRequest)
	{
		super(recordRequest.getDomain());
		this.type = recordRequest.getType();
		this.name = recordRequest.getName();
		this.content = recordRequest.getContent();
		this.ttl = recordRequest.getTtl();
		this.priority = recordRequest.getPriority();
		this.service = recordRequest.getService();
		this.srvname = recordRequest.getSrvname();
		this.protocol = recordRequest.getProtocol();
		this.weight = recordRequest.getWeight();
		this.port = recordRequest.getPort();
		this.target = recordRequest.getTarget();
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public Integer getTtl()
	{
		return ttl;
	}
	
	public Integer getPriority()
	{
		return priority;
	}
	
	public String getService()
	{
		return service;
	}
	
	public String getSrvname()
	{
		return srvname;
	}
	
	public String getProtocol()
	{
		return protocol;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	public RecordRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public RecordRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public RecordRequest setContent(String content)
	{
		this.content = content;
		return this;
	}
	
	public RecordRequest setTtl(Integer ttl)
	{
		this.ttl = ttl;
		return this;
	}
	
	public RecordRequest setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public RecordRequest setService(String service)
	{
		this.service = service;
		return this;
	}
	
	public RecordRequest setSrvname(String srvname)
	{
		this.srvname = srvname;
		return this;
	}
	
	public RecordRequest setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public RecordRequest setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
	public RecordRequest setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public RecordRequest setTarget(String target)
	{
		this.target = target;
		return this;
	}
	
	public RecordRequest setType(RecordType recordType)
	{
		this.type = recordType.get();
		return this;
	}
	
	public RecordRequest protocol(ProtocolType protocolType)
	{
		this.protocol = protocolType.get();
		return this;
	}
	
	@Override
	public RecordRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public RecordRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public RecordRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public RecordRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
