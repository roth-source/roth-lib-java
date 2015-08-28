package roth.lib.api.cloudflare.type;

public enum SecurityLevelType
{
	HELP,
	HIGH,
	MED,
	LOW,
	EOFF,
	;
	
	public String get()
	{
		return name().toLowerCase();
	}
	
}
