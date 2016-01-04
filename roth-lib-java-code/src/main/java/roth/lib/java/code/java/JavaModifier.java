package roth.lib.java.code.java;

public enum JavaModifier
{
	STATIC,
	FINAL,
	ABSTRACT,
	SYNCHRONIZED,
	TRANSIENT,
	VOLATILE,
	NATIVE,
	STRICTFP,
	DEFAULT;
	
	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
	
	public static JavaModifier parse(String value)
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
	
}
