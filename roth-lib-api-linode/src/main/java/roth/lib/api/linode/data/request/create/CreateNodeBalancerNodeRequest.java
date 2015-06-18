package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeBalancerNodeRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class CreateNodeBalancerNodeRequest extends NodeBalancerNodeRequest
{
	@Property(name = "ConfigID")
	protected  Integer configId;
	
	public CreateNodeBalancerNodeRequest(Integer configId, String label, String address)
	{
		super();
		setConfigId(configId);
		setLabel(label);
		setAddress(address);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public CreateNodeBalancerNodeRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
