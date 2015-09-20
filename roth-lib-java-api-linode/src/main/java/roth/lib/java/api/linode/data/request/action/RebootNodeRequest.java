package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeIdRequest;

@Entity
@SuppressWarnings("serial")
public class RebootNodeRequest extends NodeIdRequest
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public RebootNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public RebootNodeRequest setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
