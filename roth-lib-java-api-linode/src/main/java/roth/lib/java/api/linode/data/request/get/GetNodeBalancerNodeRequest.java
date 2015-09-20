package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeBalancerNodeIdRequest;

@Entity
@SuppressWarnings("serial")
public class GetNodeBalancerNodeRequest extends NodeBalancerNodeIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public GetNodeBalancerNodeRequest(Integer configId)
	{
		super();
		setConfigId(configId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public GetNodeBalancerNodeRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
