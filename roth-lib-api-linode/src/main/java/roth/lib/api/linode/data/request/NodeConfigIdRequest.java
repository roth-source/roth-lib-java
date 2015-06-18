package roth.lib.api.linode.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class NodeConfigIdRequest extends NodeIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public NodeConfigIdRequest()
	{
		
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public NodeConfigIdRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
