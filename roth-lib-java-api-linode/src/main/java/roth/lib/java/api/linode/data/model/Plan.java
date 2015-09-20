package roth.lib.java.api.linode.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Plan implements Serializable
{
	@Property(name = "PLANID")
	protected Integer planId;
	
	@Property(name = "PRICE")
	protected BigDecimal price;
	
	@Property(name = "HOURLY")
	protected BigDecimal hourly;
	
	@Property(name = "RAM")
	protected Integer ram;
	
	@Property(name = "XFER")
	protected Integer xfer;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "DISK")
	protected Integer disk;
	
	@Property(name = "AVAIL")
	protected LinkedHashMap<String, Integer> avail;
	
	public Plan()
	{
		
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public BigDecimal getHourly()
	{
		return hourly;
	}
	
	public Integer getRam()
	{
		return ram;
	}
	
	public Integer getXfer()
	{
		return xfer;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Integer getDisk()
	{
		return disk;
	}
	
	public LinkedHashMap<String, Integer> getAvail()
	{
		return avail;
	}
	
	public Plan setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
	public Plan setPrice(BigDecimal price)
	{
		this.price = price;
		return this;
	}
	
	public Plan setHourly(BigDecimal hourly)
	{
		this.hourly = hourly;
		return this;
	}
	
	public Plan setRam(Integer ram)
	{
		this.ram = ram;
		return this;
	}
	
	public Plan setXfer(Integer xfer)
	{
		this.xfer = xfer;
		return this;
	}
	
	public Plan setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public Plan setDisk(Integer disk)
	{
		this.disk = disk;
		return this;
	}
	
	public Plan setAvail(LinkedHashMap<String, Integer> avail)
	{
		this.avail = avail;
		return this;
	}
	
}
