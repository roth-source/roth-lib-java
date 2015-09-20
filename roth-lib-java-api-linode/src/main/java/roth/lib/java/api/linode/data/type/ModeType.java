package roth.lib.java.api.linode.data.type;

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
