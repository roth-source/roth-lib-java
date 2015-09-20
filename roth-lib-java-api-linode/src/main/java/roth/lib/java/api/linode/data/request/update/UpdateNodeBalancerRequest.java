package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeBalancerRequest;

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
