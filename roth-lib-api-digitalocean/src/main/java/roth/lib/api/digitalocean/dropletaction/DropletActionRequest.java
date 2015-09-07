package roth.lib.api.digitalocean.dropletaction;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.type.SizeType;

@Entity
@SuppressWarnings("serial")
public class DropletActionRequest implements Serializable
{
	@Property(name = "type")
	protected String type;
	
	@Property(name = "size")
	protected String size;
	
	@Property(name = "image")
	protected String image;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "kernel")
	protected Integer kernel;
	
	public DropletActionRequest()
	{
		
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getSize()
	{
		return size;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getKernel()
	{
		return kernel;
	}
	
	public DropletActionRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DropletActionRequest setSize(String size)
	{
		this.size = size;
		return this;
	}
	
	public DropletActionRequest setImage(String image)
	{
		this.image = image;
		return this;
	}
	
	public DropletActionRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DropletActionRequest setKernel(Integer kernel)
	{
		this.kernel = kernel;
		return this;
	}
	
	public DropletActionRequest setSizeType(SizeType sizeType)
	{
		size = sizeType.get();
		return this;
	}
	
}
