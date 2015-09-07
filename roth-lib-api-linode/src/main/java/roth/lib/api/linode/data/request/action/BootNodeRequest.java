package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class BootNodeRequest extends NodeIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public BootNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public BootNodeRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
