package roth.lib.service;

public class HttpServiceMethod
{
	protected String serviceName;
	protected String methodName;
	
	public HttpServiceMethod(String serviceName, String methodName)
	{
		this.serviceName = serviceName;
		this.methodName = methodName;
	}
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
	
}
