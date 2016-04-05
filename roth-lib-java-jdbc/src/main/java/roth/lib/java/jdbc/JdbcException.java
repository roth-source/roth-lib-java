package roth.lib.java.jdbc;

@SuppressWarnings("serial")
public class JdbcException extends RuntimeException
{
	
	public JdbcException(String message)
	{
		super(message);
	}
	
	public JdbcException(Throwable cause)
	{
		super(cause);
	}
	
	public JdbcException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
