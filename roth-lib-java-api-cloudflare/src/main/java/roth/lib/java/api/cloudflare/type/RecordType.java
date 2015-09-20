package roth.lib.java.api.cloudflare.type;

public enum RecordType
{
	A,
	CNAME,
	MX,
	TXT,
	SPF,
	AAAA,
	NS,
	SRV,
	LOC,
	;
	
	public String get()
	{
		return name();
	}
	
}
