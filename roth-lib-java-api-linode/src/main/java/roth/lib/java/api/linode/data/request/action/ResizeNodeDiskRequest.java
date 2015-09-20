package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeDiskIdRequest;

@Entity
@SuppressWarnings("serial")
public class ResizeNodeDiskRequest extends NodeDiskIdRequest
{
	@Property(name = "size")
	protected Integer size;
	
	public ResizeNodeDiskRequest(Integer linodeId, Integer diskId, Integer size)
	{
		super();
		setLinodeId(linodeId);
		setDiskId(diskId);
		setSize(size);
	}
	
	public Integer getSize()
	{
		return size;
	}
	
	public ResizeNodeDiskRequest setSize(Integer size)
	{
		this.size = size;
		return this;
	}
	
}
