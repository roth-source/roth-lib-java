package roth.lib.map.rdb;

@SuppressWarnings("serial")
public class RdbException extends RuntimeException
{
	
	public RdbException(String message)
	{
		super(message);
	}
	
	public RdbException(Throwable cause)
	{
		super(cause);
	}
	
	public RdbException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
