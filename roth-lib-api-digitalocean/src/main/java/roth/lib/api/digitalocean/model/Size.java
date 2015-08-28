package roth.lib.api.digitalocean.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Size implements Serializable
{
	@Property(name = "slug")
	protected String slug;
	
	@Property(name = "memory")
	protected Integer memory;
	
	@Property(name = "vcpus")
	protected Integer vcpus;
	
	@Property(name = "disk")
	protected Integer disk;
	
	@Property(name = "transfer")
	protected String transfer;
	
	@Property(name = "price_monthly")
	protected Double priceMonthly;
	
	@Property(name = "price_hourly")
	protected Double priceHourly;
	
	@Property(name = "regions")
	protected LinkedList<String> regions;
	
	public Size()
	{
		
	}
	
	public String getSlug()
	{
		return slug;
	}
	
	public Integer getMemory()
	{
		return memory;
	}
	
	public Integer getVcpus()
	{
		return vcpus;
	}
	
	public Integer getDisk()
	{
		return disk;
	}
	
	public String getTransfer()
	{
		return transfer;
	}
	
	public Double getPriceMonthly()
	{
		return priceMonthly;
	}
	
	public Double getPriceHourly()
	{
		return priceHourly;
	}
	
	public LinkedList<String> getRegions()
	{
		return regions;
	}
	
	public Size setSlug(String slug)
	{
		this.slug = slug;
		return this;
	}
	
	public Size setMemory(Integer memory)
	{
		this.memory = memory;
		return this;
	}
	
	public Size setVcpus(Integer vcpus)
	{
		this.vcpus = vcpus;
		return this;
	}
	
	public Size setDisk(Integer disk)
	{
		this.disk = disk;
		return this;
	}
	
	public Size setTransfer(String transfer)
	{
		this.transfer = transfer;
		return this;
	}
	
	public Size setPriceMonthly(Double priceMonthly)
	{
		this.priceMonthly = priceMonthly;
		return this;
	}
	
	public Size setPriceHourly(Double priceHourly)
	{
		this.priceHourly = priceHourly;
		return this;
	}
	
	public Size setRegions(LinkedList<String> regions)
	{
		this.regions = regions;
		return this;
	}
	
}
