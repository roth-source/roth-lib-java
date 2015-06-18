package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeIdRequest;

@SuppressWarnings("serial")
public class ShutdownNodeRequest extends NodeIdRequest
{
	
	public ShutdownNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
}
