package roth.lib.api.cloudflare.data.type;

public enum ClassType
{
	REGULAR		("r"),
	CRAWLER		("s"),
	THREAT		("t"),
	;
	
	private String code;
	
	ClassType(String code)
	{
		this.code = code;
	}
	
	public String get()
	{
		return code;
	}
	
}
