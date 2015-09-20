package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeRequest;

@Entity
@SuppressWarnings("serial")
public class CloneNodeRequest extends NodeRequest
{
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
	public CloneNodeRequest(Integer linodeId, Integer datacenterId, Integer planId)
	{
		super();
		setLinodeId(linodeId);
		setDatacenterId(datacenterId);
		setPlanId(planId);
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public CloneNodeRequest setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
}
