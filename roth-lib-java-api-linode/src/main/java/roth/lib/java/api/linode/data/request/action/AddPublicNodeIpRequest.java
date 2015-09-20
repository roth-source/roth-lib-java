package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.api.linode.data.request.NodeIdRequest;

@SuppressWarnings("serial")
public class AddPublicNodeIpRequest extends NodeIdRequest
{
	
	public AddPublicNodeIpRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
}
