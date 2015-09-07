package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.NodeBalancerRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class UpdateNodeBalancerRequest extends NodeBalancerRequest
{
	@Property(name = "NodeBalancerID")
	protected Integer nodeBalancerId;
	
	public UpdateNodeBalancerRequest(Integer nodeBalancerId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public UpdateNodeBalancerRequest setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
}
