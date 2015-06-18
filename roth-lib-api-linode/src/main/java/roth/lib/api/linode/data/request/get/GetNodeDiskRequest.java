package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.NodeDiskIdRequest;

@SuppressWarnings("serial")
public class GetNodeDiskRequest extends NodeDiskIdRequest
{
	
	public GetNodeDiskRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
}
