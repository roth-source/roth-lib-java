package roth.lib.api.linode.data.type;

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
