package roth.lib.java.api.cloudflare.type;

public enum RocketLoaderType
{
	OFF			("0"),
	AUTOMATIC	("a"),
	MANUAL		("m"),
	;
	
	private String code;
	
	RocketLoaderType(String code)
	{
		this.code = code;
	}
	
	public String get()
	{
		return code;
	}
	
}
