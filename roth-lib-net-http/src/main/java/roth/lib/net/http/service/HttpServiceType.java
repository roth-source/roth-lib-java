package roth.lib.net.http.service;

public enum HttpServiceType
{
	ENDPOINT,
	TASK,
	NOT_FOUND,
	;
	
	public static HttpServiceType get(String serviceName)
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
