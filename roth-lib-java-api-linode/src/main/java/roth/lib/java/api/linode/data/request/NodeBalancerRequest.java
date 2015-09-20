package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerRequest extends LinodeRequest
{
	@Property(name = "Label")
	protected String label;
	
	@Property(name = "ClientConnThrottle")
	protected Integer clientConnThrottle;
	
	public NodeBalancerRequest()
	{
		
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Integer getClientConnThrottle()
	{
		return clientConnThrottle;
	}
	
	public NodeBalancerRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeBalancerRequest setClientConnThrottle(Integer clientConnThrottle)
	{
		this.clientConnThrottle = clientConnThrottle;
		return this;
	}
	
}
