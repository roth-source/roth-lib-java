package roth.lib.net.http.type;

public enum EncodingType
{
	GZIP,
	;
	
	public static EncodingType fromString(String value)
	{
		EncodingType encodingType = null;
		if(value != null)
		{
			try
			{
				encodingType = valueOf(value.toUpperCase());
			}
			catch(Exception e)
			{
				
			}
		}
		return encodingType;
	}
}
