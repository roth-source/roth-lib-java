package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeConfigRequest;

@Entity
@SuppressWarnings("serial")
public class UpdateNodeConfigRequest extends NodeConfigRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public UpdateNodeConfigRequest(Integer linodeId, Integer configId)
	{
		super();
		setLinodeId(linodeId);
		setConfigId(configId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public UpdateNodeConfigRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
