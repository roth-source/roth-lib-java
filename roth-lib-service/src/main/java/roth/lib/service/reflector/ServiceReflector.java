package roth.lib.service.reflector;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.service.HttpService;
import roth.lib.service.annotation.ServiceMethod;
import roth.lib.util.ReflectionUtil;

public class ServiceReflector
{
	protected Class<?> serviceClass;
	protected String serviceName;
	protected LinkedHashMap<String, ServiceMethodReflector> serviceMethodReflectorMap = new LinkedHashMap<String, ServiceMethodReflector>();
	
	public ServiceReflector(Class<?> serviceClass, String serviceName)
	{
		this.serviceClass = serviceClass;
		this.serviceName = serviceName;
		for(ServiceMethodReflector methodReflector : getServiceMethodReflectors(serviceClass))
		{
			serviceMethodReflectorMap.put(methodReflector.getName(), methodReflector);
		}
	}
	
	protected static LinkedList<ServiceMethodReflector> getServiceMethodReflectors(Class<?> serviceClass)
	{
		LinkedList<ServiceMethodReflector> serviceMethodReflectors = new LinkedList<ServiceMethodReflector>();
		for(java.lang.reflect.Method method : ReflectionUtil.getMethods(serviceClass, HttpService.class))
		{
			ServiceMethod serviceMethod = ReflectionUtil.getAnnotation(serviceClass, method, ServiceMethod.class);
			if(serviceMethod != null)
			{
				method.setAccessible(true);
				ServiceMethodReflector serviceMethodReflector = new ServiceMethodReflector();
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
	
	public LinkedHashMap<String, ServiceMethodReflector> getServiceMethodReflectorMap()
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
	
	public ServiceReflector setMethodReflectorMap(LinkedHashMap<String, ServiceMethodReflector> serviceMethodReflectorMap)
	{
		this.serviceMethodReflectorMap = serviceMethodReflectorMap;
		return this;
	}
	
}
