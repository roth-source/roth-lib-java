package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Kernel;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class KernelsResponse implements Serializable
{
	@Property(name = "kernels")
	protected LinkedList<Kernel> kernels;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public KernelsResponse()
	{
		
	}
	
	public LinkedList<Kernel> getKernels()
	{
		return kernels;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public KernelsResponse setKernels(LinkedList<Kernel> kernels)
	{
		this.kernels = kernels;
		return this;
	}
	
	public KernelsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
