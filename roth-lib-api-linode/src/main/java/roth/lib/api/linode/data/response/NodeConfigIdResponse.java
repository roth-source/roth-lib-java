package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

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
