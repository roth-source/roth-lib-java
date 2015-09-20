package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Ip implements Serializable
{
	@Property(name = "ip")
	protected String ip;
	
	@Property(name = "classification")
	protected String classification;
	
	@Property(name = "hits")
	protected Integer hits;
	
	@Property(name = "latitude")
	protected Double latitude;
	
	@Property(name = "longitude")
	protected Double longitude;
	
	@Property(name = "zone_name")
	protected String zoneName;
	
	public Ip()
	{
		
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public String getClassification()
	{
		return classification;
	}
	
	public Integer getHits()
	{
		return hits;
	}
	
	public Double getLatitude()
	{
		return latitude;
	}
	
	public Double getLongitude()
	{
		return longitude;
	}
	
	public String getZoneName()
	{
		return zoneName;
	}
	
	public Ip setIp(String ip)
	{
		this.ip = ip;
		return this;
	}
	
	public Ip setClassification(String classification)
	{
		this.classification = classification;
		return this;
	}
	
	public Ip setHits(Integer hits)
	{
		this.hits = hits;
		return this;
	}
	
	public Ip setLatitude(Double latitude)
	{
		this.latitude = latitude;
		return this;
	}
	
	public Ip setLongitude(Double longitude)
	{
		this.longitude = longitude;
		return this;
	}
	
	public Ip setZoneName(String zoneName)
	{
		this.zoneName = zoneName;
		return this;
	}
	
}
