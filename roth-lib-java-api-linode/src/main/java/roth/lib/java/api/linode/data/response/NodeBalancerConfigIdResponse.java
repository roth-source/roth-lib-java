package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeBalancerConfigIdResponse implements Serializable
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public NodeBalancerConfigIdResponse()
	{
		
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public NodeBalancerConfigIdResponse setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
