package roth.lib.java.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.model.Settings;

@Entity
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
