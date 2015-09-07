package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.NodeBalancerConfigRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class UpdateNodeBalancerConfigRequest extends NodeBalancerConfigRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public UpdateNodeBalancerConfigRequest(Integer configId)
	{
		super();
		setConfigId(configId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public UpdateNodeBalancerConfigRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
