package roth.lib.java.service.reflector;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import roth.lib.java.service.HttpService;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.util.ReflectionUtil;

public class ServiceReflector
{
	protected Class<? extends HttpService> klass;
	protected String name;
	protected LinkedHashMap<String, MethodReflector> methodReflectorMap = new LinkedHashMap<String, MethodReflector>();
	
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
				methodReflectorMap.put(methodReflector.getName(), methodReflector);
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
	
	public LinkedHashMap<String, MethodReflector> getMethodReflectorMap()
	{
		return methodReflectorMap;
	}
	
}
