package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Kernel implements Serializable
{
	@Property(name = "KERNELID")
	protected Integer kernelId;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "ISXEN")
	protected Boolean xen;
	
	@Property(name = "ISPVOPS")
	protected Boolean pvops;
	
	public Kernel()
	{
		
	}
	
	public Integer getKernelId()
	{
		return kernelId;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Boolean getXen()
	{
		return xen;
	}
	
	public Boolean getPvops()
	{
		return pvops;
	}
	
	public Kernel setKernelId(Integer kernelId)
	{
		this.kernelId = kernelId;
		return this;
	}
	
	public Kernel setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public Kernel setXen(Boolean xen)
	{
		this.xen = xen;
		return this;
	}
	
	public Kernel setPvops(Boolean pvops)
	{
		this.pvops = pvops;
		return this;
	}
	
}
