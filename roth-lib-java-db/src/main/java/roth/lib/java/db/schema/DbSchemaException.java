package roth.lib.java.db.schema;

@SuppressWarnings("serial")
public class DbSchemaException extends RuntimeException
{
	
	public DbSchemaException(String message)
	{
		super(message);
	}
	
	public DbSchemaException(Throwable cause)
	{
		super(cause);
	}
	
	public DbSchemaException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
