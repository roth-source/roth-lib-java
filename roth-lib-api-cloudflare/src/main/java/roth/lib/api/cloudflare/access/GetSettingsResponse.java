package roth.lib.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.Settings;

@SuppressWarnings("serial")
public class GetSettingsResponse implements Serializable
{
	@Property(name = "result")
	protected Settings result;
	
	public GetSettingsResponse()
	{
		
	}
	
	public Settings getResult()
	{
		return result;
	}
	
}
