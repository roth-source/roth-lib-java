package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class IpAction implements Serializable
{
	@Property(name = "ip")
	protected String ip;
	
	@Property(name = "action")
	protected String action;
	
	public IpAction()
	{
		
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public String getAction()
	{
		return action;
	}
	
	public IpAction setIp(String ip)
	{
		this.ip = ip;
		return this;
	}
	
	public IpAction setAction(String action)
	{
		this.action = action;
		return this;
	}
	
}
