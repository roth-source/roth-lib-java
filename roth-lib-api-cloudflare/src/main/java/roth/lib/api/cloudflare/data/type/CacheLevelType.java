package roth.lib.api.cloudflare.data.type;

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
