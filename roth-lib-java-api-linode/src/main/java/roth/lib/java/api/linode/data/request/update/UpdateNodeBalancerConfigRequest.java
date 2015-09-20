package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeBalancerConfigRequest;

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
