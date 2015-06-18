package roth.lib.api.cloudflare.data.type;

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
