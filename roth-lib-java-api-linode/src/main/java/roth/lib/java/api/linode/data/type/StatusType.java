package roth.lib.java.api.linode.data.type;

public enum StatusType
{
	DISABLED	(0),
	ACTIVE		(1),
	EDIT		(2),
	;
	
	private int code;
	
	StatusType(int code)
	{
		this.code = code;
	}
	
	public int get()
	{
		return code;
	}
	
}
