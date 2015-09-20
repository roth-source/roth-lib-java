package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeBalancerNodeRequest;

@Entity
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
