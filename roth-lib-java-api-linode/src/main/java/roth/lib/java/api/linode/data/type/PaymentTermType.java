package roth.lib.java.api.linode.data.type;

public enum PaymentTermType
{
	_1		(1),
	_12		(12),
	_24		(24),
	;
	
	private int code;
	
	PaymentTermType(int code)
	{
		this.code = code;
	}
	
	public int get()
	{
		return code;
	}
	
}
