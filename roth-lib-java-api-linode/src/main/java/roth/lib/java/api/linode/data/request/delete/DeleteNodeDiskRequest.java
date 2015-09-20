package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.api.linode.data.request.NodeDiskIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeDiskRequest extends NodeDiskIdRequest
{
	
	public DeleteNodeDiskRequest(Integer linodeId, Integer diskId)
	{
		super();
		setLinodeId(linodeId);
		setDiskId(diskId);
	}
	
}
