package roth.lib.java.table;

@SuppressWarnings("serial")
public class TableException extends RuntimeException
{
	
	public TableException(String message)
	{
		super(message);
	}
	
	public TableException(Throwable cause)
	{
		super(cause);
	}
	
	public TableException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
