package roth.lib.net.http.header.type;

public enum EncodingType
{
	GZIP,
	;
	
	public static EncodingType get(String value)
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
