package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeIdResponse implements Serializable
{
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
	public NodeIdResponse()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public NodeIdResponse setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
}
