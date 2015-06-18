package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Property;

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
