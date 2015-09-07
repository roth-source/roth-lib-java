package roth.lib.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class BandwidthServed implements Serializable
{
	@Property(name = "cloudflare")
	protected Double cloudflare;
	
	@Property(name = "user")
	protected Double user;
	
	public BandwidthServed()
	{
		
	}
	
	public Double getCloudflare()
	{
		return cloudflare;
	}
	
	public Double getUser()
	{
		return user;
	}
	
	public BandwidthServed setCloudflare(Double cloudflare)
	{
		this.cloudflare = cloudflare;
		return this;
	}
	
	public BandwidthServed setUser(Double user)
	{
		this.user = user;
		return this;
	}
	
}
