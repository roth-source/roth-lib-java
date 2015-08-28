package roth.lib.api.cloudflare.modify;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.IpAction;

@SuppressWarnings("serial")
public class UnlistIpResponse implements Serializable
{
	@Property(name = "result")
	protected IpAction result;
	
	public UnlistIpResponse()
	{
		
	}
	
	public IpAction getResult()
	{
		return result;
	}
	
}
