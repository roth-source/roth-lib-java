package roth.lib.api.cloudflare.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RecordProps implements Serializable
{
	@Property(name = "proxiable")
	protected Integer proxiable;
	
	@Property(name = "cloud_on")
	protected Integer cloudOn;
	
	@Property(name = "cf_open")
	protected Integer cfOpen;
	
	@Property(name = "ssl")
	protected Integer ssl;
	
	@Property(name = "expired_ssl")
	protected Integer expiredSsl;
	
	@Property(name = "pending_ssl")
	protected Integer pendingSsl;
	
	public RecordProps()
	{
		
	}
	
	public Integer getProxiable()
	{
		return proxiable;
	}
	
	public Integer getCloudOn()
	{
		return cloudOn;
	}
	
	public Integer getCfOpen()
	{
		return cfOpen;
	}
	
	public Integer getSsl()
	{
		return ssl;
	}
	
	public Integer getExpiredSsl()
	{
		return expiredSsl;
	}
	
	public Integer getPendingSsl()
	{
		return pendingSsl;
	}
	
	public RecordProps setProxiable(Integer proxiable)
	{
		this.proxiable = proxiable;
		return this;
	}
	
	public RecordProps setCloudOn(Integer cloudOn)
	{
		this.cloudOn = cloudOn;
		return this;
	}
	
	public RecordProps setCfOpen(Integer cfOpen)
	{
		this.cfOpen = cfOpen;
		return this;
	}
	
	public RecordProps setSsl(Integer ssl)
	{
		this.ssl = ssl;
		return this;
	}
	
	public RecordProps setExpiredSsl(Integer expiredSsl)
	{
		this.expiredSsl = expiredSsl;
		return this;
	}
	
	public RecordProps setPendingSsl(Integer pendingSsl)
	{
		this.pendingSsl = pendingSsl;
		return this;
	}
	
}
