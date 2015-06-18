package roth.lib.api.linode.data.type;

public enum ModeType
{
	LINODE_NEW,
	LINODE_RESIZE,
	NODEBALANCER_NEW,
	;
	
	public String get()
	{
		return name().toLowerCase();
	}
	
}
