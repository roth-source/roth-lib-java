package roth.lib.java.api.cloudflare.type;

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
