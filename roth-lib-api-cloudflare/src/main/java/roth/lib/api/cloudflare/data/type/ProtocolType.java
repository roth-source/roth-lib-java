package roth.lib.api.cloudflare.data.type;

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
