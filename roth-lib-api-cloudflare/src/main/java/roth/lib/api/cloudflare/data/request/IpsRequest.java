package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.ClassType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class IpsRequest extends DomainRequest
{
	@Property(name = "hours")
	protected Integer hours;
	
	@Property(name = "class")
	protected String klass;
	
	@Property(name = "geo")
	protected Integer geo;
	
	public IpsRequest(String domain)
	{
		super(domain);
	}
	
	public Integer getHours()
	{
		return hours;
	}
	
	public String getKlass()
	{
		return klass;
	}
	
	public Integer getGeo()
	{
		return geo;
	}
	
	public IpsRequest setHours(int hours)
	{
		if(0 < hours && hours <= 48)
		{
			this.hours = hours;
		}
		return this;
	}
	
	public IpsRequest setKlass(String klass)
	{
		this.klass = klass;
		return this;
	}
	
	public IpsRequest setClassType(ClassType classType)
	{
		this.klass = classType.get();
		return this;
	}
	
	public IpsRequest setGeo(boolean geo)
	{
		if(geo)
		{
			this.geo =  1;
		}
		return this;
	}
	
	@Override
	public IpsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public IpsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public IpsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public IpsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
