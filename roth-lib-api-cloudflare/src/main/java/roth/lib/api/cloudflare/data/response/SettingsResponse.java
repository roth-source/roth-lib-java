package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.Settings;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SettingsResponse implements Serializable
{
	@Property(name = "result")
	protected Settings result;
	
	public SettingsResponse()
	{
		
	}
	
	public Settings getResult()
	{
		return result;
	}
	
}
