package roth.lib.api.cloudflare.type;

public enum CacheLevelType
{
	BASIC,
	AGG,
	;
	
	public String get()
	{
		return name().toLowerCase();
	}
	
}
