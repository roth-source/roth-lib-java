package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeIdRequest extends LinodeRequest
{
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
	public NodeIdRequest()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public NodeIdRequest setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
}
