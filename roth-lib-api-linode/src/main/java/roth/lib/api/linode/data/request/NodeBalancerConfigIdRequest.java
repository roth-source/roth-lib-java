package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerConfigIdRequest extends NodeBalancerIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public NodeBalancerConfigIdRequest()
	{
		
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public NodeBalancerConfigIdRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
