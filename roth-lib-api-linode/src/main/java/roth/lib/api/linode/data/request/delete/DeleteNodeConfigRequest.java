package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.NodeConfigIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeConfigRequest extends NodeConfigIdRequest
{
	
	public DeleteNodeConfigRequest(Integer linodeId, Integer configId)
	{
		super();
		setLinodeId(linodeId);
		setConfigId(configId);
	}
	
}
