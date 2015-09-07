package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.NodeBalancerNodeIdRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

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
