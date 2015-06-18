package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Size;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SizesResponse implements Serializable
{
	@Property(name = "sizes")
	protected LinkedList<Size> sizes;
	
	public SizesResponse()
	{
		
	}
	
	public LinkedList<Size> getSizes()
	{
		return sizes;
	}
	
	public SizesResponse setSizes(LinkedList<Size> sizes)
	{
		this.sizes = sizes;
		return this;
	}
	
}
