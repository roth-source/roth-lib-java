package roth.lib.java.api.linode.data.type;

public enum DomainType
{
	MASTER,
	SLAVE,
	;
	
	public String get()
	{
		return name().toLowerCase();
	}
	
}
