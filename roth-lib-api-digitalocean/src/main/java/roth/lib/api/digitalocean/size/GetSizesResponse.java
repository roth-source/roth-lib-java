package roth.lib.api.digitalocean.size;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Size;

@Entity
@SuppressWarnings("serial")
public class GetSizesResponse implements Serializable
{
	@Property(name = "sizes")
	protected LinkedList<Size> sizes;
	
	public GetSizesResponse()
	{
		
	}
	
	public LinkedList<Size> getSizes()
	{
		return sizes;
	}
	
	public GetSizesResponse setSizes(LinkedList<Size> sizes)
	{
		this.sizes = sizes;
		return this;
	}
	
}
