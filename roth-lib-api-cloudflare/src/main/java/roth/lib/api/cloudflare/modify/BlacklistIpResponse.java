package roth.lib.api.cloudflare.modify;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.IpAction;

@Entity
@SuppressWarnings("serial")
public class BlacklistIpResponse implements Serializable
{
	@Property(name = "result")
	protected IpAction result;
	
	public BlacklistIpResponse()
	{
		
	}
	
	public IpAction getResult()
	{
		return result;
	}
	
}
