package roth.lib.java.http.type;

public enum AuthorizationType
{
	BASIC,
	BEARER,
	;
	
	@Override
	public String toString()
	{
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
	
}
