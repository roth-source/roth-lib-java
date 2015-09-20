package roth.lib.java.api.cloudflare.type;

public enum ProtocolType
{
	TCP,
	UDP,
	TLS,
	;
	
	public String get()
	{
		return "_" + name().toLowerCase();
	}
	
}
