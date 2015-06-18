package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeDiskIdRequest;
import roth.lib.annotation.Property;

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
