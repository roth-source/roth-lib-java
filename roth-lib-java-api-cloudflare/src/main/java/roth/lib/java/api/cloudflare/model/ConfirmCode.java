package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class ConfirmCode implements Serializable
{
	@Property(name = "zone_deactivate")
	protected String zoneDeactivate;
	
	@Property(name = "zone_dev_mode1")
	protected String zoneDevMode1;
	
	public ConfirmCode()
	{
		
	}
	
	public String getZoneDeactivate()
	{
		return zoneDeactivate;
	}
	
	public String getZoneDevMode1()
	{
		return zoneDevMode1;
	}
	
	public ConfirmCode setZoneDeactivate(String zoneDeactivate)
	{
		this.zoneDeactivate = zoneDeactivate;
		return this;
	}
	
	public ConfirmCode setZoneDevMode1(String zoneDevMode1)
	{
		this.zoneDevMode1 = zoneDevMode1;
		return this;
	}
	
}
