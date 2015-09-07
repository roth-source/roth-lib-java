package roth.lib.api.digitalocean.droplet;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Kernel;
import roth.lib.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetKernelsResponse implements Serializable
{
	@Property(name = "kernels")
	protected LinkedList<Kernel> kernels;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetKernelsResponse()
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
	
	public GetKernelsResponse setKernels(LinkedList<Kernel> kernels)
	{
		this.kernels = kernels;
		return this;
	}
	
	public GetKernelsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
