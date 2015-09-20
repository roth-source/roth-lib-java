package roth.lib.java.service;

public enum HttpServiceType
{
	ENDPOINT,
	TASK,
	NOT_FOUND,
	;
	
	public static HttpServiceType fromString(String serviceName)
	{
		HttpServiceType serviceType = NOT_FOUND;
		try
		{
			serviceType = HttpServiceType.valueOf(serviceName.toUpperCase());
		}
		catch(Exception e)
		{
			
		}
		return serviceType;
	}
	
}
