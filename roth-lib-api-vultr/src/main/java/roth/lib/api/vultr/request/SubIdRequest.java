package roth.lib.api.vultr.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class SubIdRequest extends VultrRequest
{
	@Property(name = "SUBID")
	protected String subId;
	
	public SubIdRequest(String subId)
	{
		this.subId = subId;
	}
	
	public String getSubId()
	{
		return subId;
	}
	
	public SubIdRequest setSubId(String subId)
	{
		this.subId = subId;
		return this;
	}
	
}
