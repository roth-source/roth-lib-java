package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.cloudflare.data.model.Ip;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class IpsResponse implements Serializable
{
	@Property(name = "ips")
	protected LinkedList<Ip> ips = new LinkedList<Ip>();
	
	public IpsResponse()
	{
		
	}
	
	public LinkedList<Ip> getIps()
	{
		return ips;
	}
	
}
