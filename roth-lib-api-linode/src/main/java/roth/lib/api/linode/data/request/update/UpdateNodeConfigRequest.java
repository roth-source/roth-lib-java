package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.NodeConfigRequest;
import roth.lib.annotation.Property;

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
