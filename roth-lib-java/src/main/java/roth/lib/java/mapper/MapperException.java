package roth.lib.java.mapper;

@SuppressWarnings("serial")
public class MapperException extends RuntimeException
{
	
	public MapperException(String message)
	{
		super(message);
	}
	
	public MapperException(Throwable cause)
	{
		super(cause);
	}
	
	public MapperException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
