package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeDiskIdResponse implements Serializable
{
	@Property(name = "DiskID")
	protected Integer diskId;
	
	public NodeDiskIdResponse()
	{
		
	}
	
	public Integer getDiskId()
	{
		return diskId;
	}
	
	public NodeDiskIdResponse setDiskId(Integer diskId)
	{
		this.diskId = diskId;
		return this;
	}
	
}
