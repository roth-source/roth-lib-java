package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.IntervalType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class StatsRequest extends DomainRequest
{
	@Property(name = "interval")
	protected Integer interval;
	
	public StatsRequest(String domain, IntervalType intervalType)
	{
		super(domain);
		setInterval(intervalType);
	}
	
	public Integer getInterval()
	{
		return interval;
	}
	
	public StatsRequest setInterval(Integer interval)
	{
		this.interval = interval;
		return this;
	}
	
	public StatsRequest setInterval(IntervalType intervalType)
	{
		this.interval = intervalType.get();
		return this;
	}
	
	@Override
	public StatsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public StatsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public StatsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public StatsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
