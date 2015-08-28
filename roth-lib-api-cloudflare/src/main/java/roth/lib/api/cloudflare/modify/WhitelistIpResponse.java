package roth.lib.api.cloudflare.modify;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.IpAction;

@SuppressWarnings("serial")
public class WhitelistIpResponse implements Serializable
{
	@Property(name = "result")
	protected IpAction result;
	
	public WhitelistIpResponse()
	{
		
	}
	
	public IpAction getResult()
	{
		return result;
	}
	
}
