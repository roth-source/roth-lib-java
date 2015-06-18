package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.IpAction;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class IpActionResponse implements Serializable
{
	@Property(name = "result")
	protected IpAction result;
	
	public IpActionResponse()
	{
		
	}
	
	public IpAction getResult()
	{
		return result;
	}
	
}
