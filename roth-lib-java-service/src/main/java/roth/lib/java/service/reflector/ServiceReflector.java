package roth.lib.java.service.reflector;

import java.lang.reflect.Method;
import roth.lib.java.lang.Map;

import roth.lib.java.service.HttpService;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.util.ReflectionUtil;

public class ServiceReflector
{
	protected Class<? extends HttpService> klass;
	protected String name;
	protected Map<String, MethodReflector> methodReflectorMap = new Map<String, MethodReflector>();
	
	public ServiceReflector(Class<? extends HttpService> klass, String name)
	{
		this.klass = klass;
		this.name = name;
		for(Method method : ReflectionUtil.getMethods(klass, HttpService.class))
		{
			ServiceMethod serviceMethod = method.getDeclaredAnnotation(ServiceMethod.class);
			if(serviceMethod != null)
			{
				MethodReflector methodReflector = new MethodReflector(this, method, serviceMethod);
				methodReflectorMap.put(methodReflector.getMethodName(), methodReflector);
			}
		}
	}
	
	public HttpService service()
	{
		HttpService service = null;
		try
		{
			service = klass.newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return service;
	}
	
	public Class<? extends HttpService> getServiceClass()
	{
		return klass;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Map<String, MethodReflector> getMethodReflectorMap()
	{
		return methodReflectorMap;
	}
	
}
