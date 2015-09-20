package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.NodeConfigRequest;

@SuppressWarnings("serial")
public class CreateNodeConfigRequest extends NodeConfigRequest
{
	
	public CreateNodeConfigRequest(Integer linodeId, Integer kernelId, String label)
	{
		super();
		setLinodeId(linodeId);
		setKernelId(kernelId);
		setLabel(label);
	}
	
}
