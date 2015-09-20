package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeConfigIdResponse implements Serializable
{
	@Property(name = "ConfigID")
	protected Integer configId;
	
	public NodeConfigIdResponse()
	{
		
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public NodeConfigIdResponse setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
}
