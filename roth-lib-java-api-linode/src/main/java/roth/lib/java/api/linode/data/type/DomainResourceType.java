package roth.lib.java.api.linode.data.type;

public enum DomainResourceType
{
	NS,
	MX,
	A,
	AAAA,
	CNAME,
	TXT,
	SRV
	;
	
	public String get()
	{
		return name();
	}
	
}
