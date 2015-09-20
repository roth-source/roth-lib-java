package roth.lib.java.api.cloudflare.access;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.DomainRequest;
import roth.lib.java.api.cloudflare.type.IntervalType;

@Entity
@SuppressWarnings("serial")
public class GetStatsRequest extends DomainRequest
{
	@Property(name = "interval")
	protected Integer interval;
	
	public GetStatsRequest(String domain, IntervalType intervalType)
	{
		super(domain);
		setInterval(intervalType);
	}
	
	public Integer getInterval()
	{
		return interval;
	}
	
	public GetStatsRequest setInterval(Integer interval)
	{
		this.interval = interval;
		return this;
	}
	
	public GetStatsRequest setInterval(IntervalType intervalType)
	{
		this.interval = intervalType.get();
		return this;
	}
	
	@Override
	public GetStatsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public GetStatsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public GetStatsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public GetStatsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
