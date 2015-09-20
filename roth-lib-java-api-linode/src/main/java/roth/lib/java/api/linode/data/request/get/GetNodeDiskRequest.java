package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.api.linode.data.request.NodeDiskIdRequest;

@SuppressWarnings("serial")
public class GetNodeDiskRequest extends NodeDiskIdRequest
{
	
	public GetNodeDiskRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
}
