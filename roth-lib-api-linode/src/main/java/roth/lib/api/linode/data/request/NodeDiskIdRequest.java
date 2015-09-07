package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeDiskIdRequest extends NodeIdRequest
{
	@Property(name = "DiskID")
	protected Integer diskId;
	
	public NodeDiskIdRequest()
	{
		
	}
	
	public Integer getDiskId()
	{
		return diskId;
	}
	
	public NodeDiskIdRequest setDiskId(Integer diskId)
	{
		this.diskId = diskId;
		return this;
	}
	
}
