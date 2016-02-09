package roth.lib.java.db.config;

@SuppressWarnings("serial")
public class DbConfigException extends RuntimeException
{
	
	public DbConfigException(String message)
	{
		super(message);
	}
	
	public DbConfigException(Throwable cause)
	{
		super(cause);
	}
	
	public DbConfigException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
