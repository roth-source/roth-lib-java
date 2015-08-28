package roth.lib.api.digitalocean.type;

public enum DomainRecordType
{
	A,
	AAAA,
	CNAME,
	TXT,
	SRV,
	MX,
	NS,
	;
	
	public String get()
	{
		return name();
	}
	
}
