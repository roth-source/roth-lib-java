package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeDiskIdRequest;

@SuppressWarnings("serial")
public class DuplicateNodeDiskRequest extends NodeDiskIdRequest
{
	
	public DuplicateNodeDiskRequest(Integer linodeId, Integer diskId)
	{
		super();
		setLinodeId(linodeId);
		setDiskId(diskId);
	}
	
}
