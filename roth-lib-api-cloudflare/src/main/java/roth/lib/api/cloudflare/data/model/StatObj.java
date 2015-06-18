package roth.lib.api.cloudflare.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class StatObj implements Serializable
{
	@Property(name = "cachedServerTime")
	protected Calendar cachedServerTime;
	
	@Property(name = "cachedExpryTime")
	protected Calendar cachedExpryTime;
	
	@Property(name = "trafficBreakdown")
	protected TrafficBreakdown trafficBreakdown;
	
	@Property(name = "bandwidthServed")
	protected BandwidthServed bandwidthServed;
	
	@Property(name = "requestsServed")
	protected RequestsServed requestsServed;
	
	@Property(name = "pro_zone")
	protected Boolean proZone;
	
	@Property(name = "pageLoadTime")
	protected Calendar pageLoadTime;
	
	@Property(name = "currentServerTime")
	protected Calendar currentServerTime;
	
	@Property(name = "interval")
	protected Integer interval;
	
	@Property(name = "zoneCDate")
	protected Calendar zoneCDate;
	
	@Property(name = "userSecuritySetting")
	protected String userSecuritySetting;
	
	@Property(name = "dev_mode")
	protected Integer devMode;
	
	@Property(name = "ipv46")
	protected Integer ipv46;
	
	@Property(name = "ob")
	protected Integer ob;
	
	@Property(name = "cache_lvl")
	protected String cacheLvl;
	
	public StatObj()
	{
		
	}
	
	public Calendar getCachedServerTime()
	{
		return cachedServerTime;
	}
	
	public Calendar getCachedExpryTime()
	{
		return cachedExpryTime;
	}
	
	public TrafficBreakdown getTrafficBreakdown()
	{
		return trafficBreakdown;
	}
	
	public BandwidthServed getBandwidthServed()
	{
		return bandwidthServed;
	}
	
	public RequestsServed getRequestsServed()
	{
		return requestsServed;
	}
	
	public Boolean getProZone()
	{
		return proZone;
	}
	
	public Calendar getPageLoadTime()
	{
		return pageLoadTime;
	}
	
	public Calendar getCurrentServerTime()
	{
		return currentServerTime;
	}
	
	public Integer getInterval()
	{
		return interval;
	}
	
	public Calendar getZoneCDate()
	{
		return zoneCDate;
	}
	
	public String getUserSecuritySetting()
	{
		return userSecuritySetting;
	}
	
	public Integer getDevMode()
	{
		return devMode;
	}
	
	public Integer getIpv46()
	{
		return ipv46;
	}
	
	public Integer getOb()
	{
		return ob;
	}
	
	public String getCacheLvl()
	{
		return cacheLvl;
	}
	
	public StatObj setCachedServerTime(Calendar cachedServerTime)
	{
		this.cachedServerTime = cachedServerTime;
		return this;
	}
	
	public StatObj setCachedExpryTime(Calendar cachedExpryTime)
	{
		this.cachedExpryTime = cachedExpryTime;
		return this;
	}
	
	public StatObj setTrafficBreakdown(TrafficBreakdown trafficBreakdown)
	{
		this.trafficBreakdown = trafficBreakdown;
		return this;
	}
	
	public StatObj setBandwidthServed(BandwidthServed bandwidthServed)
	{
		this.bandwidthServed = bandwidthServed;
		return this;
	}
	
	public StatObj setRequestsServed(RequestsServed requestsServed)
	{
		this.requestsServed = requestsServed;
		return this;
	}
	
	public StatObj setProZone(Boolean proZone)
	{
		this.proZone = proZone;
		return this;
	}
	
	public StatObj setPageLoadTime(Calendar pageLoadTime)
	{
		this.pageLoadTime = pageLoadTime;
		return this;
	}
	
	public StatObj setCurrentServerTime(Calendar currentServerTime)
	{
		this.currentServerTime = currentServerTime;
		return this;
	}
	
	public StatObj setInterval(Integer interval)
	{
		this.interval = interval;
		return this;
	}
	
	public StatObj setZoneCDate(Calendar zoneCDate)
	{
		this.zoneCDate = zoneCDate;
		return this;
	}
	
	public StatObj setUserSecuritySetting(String userSecuritySetting)
	{
		this.userSecuritySetting = userSecuritySetting;
		return this;
	}
	
	public StatObj setDevMode(Integer devMode)
	{
		this.devMode = devMode;
		return this;
	}
	
	public StatObj setIpv46(Integer ipv46)
	{
		this.ipv46 = ipv46;
		return this;
	}
	
	public StatObj setOb(Integer ob)
	{
		this.ob = ob;
		return this;
	}
	
	public StatObj setCacheLvl(String cacheLvl)
	{
		this.cacheLvl = cacheLvl;
		return this;
	}
	
}
