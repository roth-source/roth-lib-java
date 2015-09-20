package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.api.linode.data.request.NodeDiskIdRequest;

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
