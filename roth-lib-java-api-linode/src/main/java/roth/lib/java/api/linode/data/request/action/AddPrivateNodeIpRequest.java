package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.api.linode.data.request.NodeIdRequest;

@SuppressWarnings("serial")
public class AddPrivateNodeIpRequest extends NodeIdRequest
{
	
	public AddPrivateNodeIpRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
}
