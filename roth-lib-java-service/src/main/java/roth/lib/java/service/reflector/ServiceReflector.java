package roth.lib.java.service.reflector;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.service.HttpService;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.util.ReflectionUtil;

public class ServiceReflector
{
	protected Class<?> serviceClass;
	protected String serviceName;
	protected LinkedHashMap<String, MethodReflector> serviceMethodReflectorMap = new LinkedHashMap<String, MethodReflector>();
	
	public ServiceReflector(Class<?> serviceClass, String serviceName)
	{
		this.serviceClass = serviceClass;
		this.serviceName = serviceName;
		for(MethodReflector methodReflector : getServiceMethodReflectors(serviceClass))
		{
			serviceMethodReflectorMap.put(methodReflector.getName(), methodReflector);
		}
	}
	
	protected static LinkedList<MethodReflector> getServiceMethodReflectors(Class<?> serviceClass)
	{
		LinkedList<MethodReflector> serviceMethodReflectors = new LinkedList<MethodReflector>();
		for(java.lang.reflect.Method method : ReflectionUtil.getMethods(serviceClass, HttpService.class))
		{
			ServiceMethod serviceMethod = ReflectionUtil.getAnnotation(serviceClass, method, ServiceMethod.class);
			if(serviceMethod != null)
			{
				method.setAccessible(true);
				MethodReflector serviceMethodReflector = new MethodReflector();
				String methodName = serviceMethod.name();
				if(methodName == null || methodName.isEmpty())
				{
					methodName = method.getName();
				}
				serviceMethodReflector.setName(methodName);
				String context = serviceMethod.context();
				serviceMethodReflector.setContext(context != null && !context.isEmpty() ? context : null);
				serviceMethodReflector.setAjax(serviceMethod.ajax());
				serviceMethodReflector.setApi(serviceMethod.api());
				serviceMethodReflector.setAuthenticated(serviceMethod.authenticated());
				serviceMethodReflector.setPost(serviceMethod.post());
				serviceMethodReflector.setGet(serviceMethod.get());
				serviceMethodReflector.setPut(serviceMethod.put());
				serviceMethodReflector.setDelete(serviceMethod.delete());
				serviceMethodReflector.setGzippedInput(serviceMethod.gzippedInput());
				serviceMethodReflector.setMethod(method);
				serviceMethodReflectors.add(serviceMethodReflector);
			}
		}
		return serviceMethodReflectors;
	}
	
	public Class<?> getServiceClass()
	{
		return serviceClass;
	}
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public LinkedHashMap<String, MethodReflector> getServiceMethodReflectorMap()
	{
		return serviceMethodReflectorMap;
	}
	
	public ServiceReflector setServiceClass(Class<?> serviceClass)
	{
		this.serviceClass = serviceClass;
		return this;
	}
	
	public ServiceReflector setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
		return this;
	}
	
	public ServiceReflector setMethodReflectorMap(LinkedHashMap<String, MethodReflector> serviceMethodReflectorMap)
	{
		this.serviceMethodReflectorMap = serviceMethodReflectorMap;
		return this;
	}
	
}
