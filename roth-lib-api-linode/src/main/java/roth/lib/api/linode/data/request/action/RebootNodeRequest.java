package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RebootNodeRequest extends NodeIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public RebootNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public RebootNodeRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
