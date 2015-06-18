package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.NodeDiskIdRequest;

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
