package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
