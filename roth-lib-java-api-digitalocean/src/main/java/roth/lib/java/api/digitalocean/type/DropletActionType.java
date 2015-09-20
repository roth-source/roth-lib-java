package roth.lib.java.api.digitalocean.type;

import roth.lib.java.api.digitalocean.dropletaction.DropletActionRequest;

public enum DropletActionType
{
	REBOOT,
	POWER_CYCLE,
	SHUTDOWN,
	POWER_OFF,
	POWER_ON,
	PASSWORD_RESET,
	RESIZE,
	RESTORE,
	REBUILD,
	RENAME,
	CHANGE_KERNEL,
	ENABLE_IPV6,
	DISABLE_BACKUPS,
	ENABLE_PRIVATE_NETWORKING,
	;
	
	public String getName()
	{
		return name().toLowerCase();
	}
	
	public DropletActionRequest getDropletActionRequest()
	{
		return new DropletActionRequest().setType(getName());
	}
	
}
