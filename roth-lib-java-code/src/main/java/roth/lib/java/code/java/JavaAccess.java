package roth.lib.java.code.java;

public enum JavaAccess
{
	PUBLIC,
	PROTECTED,
	PRIVATE;
	
	public static JavaAccess parse(String value)
	{
		try
		{
			return valueOf(value);
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
	
}
