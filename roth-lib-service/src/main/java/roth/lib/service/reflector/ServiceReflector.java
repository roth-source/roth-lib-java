package roth.lib.service.reflector;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.service.HttpService;
import roth.lib.service.annotation.Method;
import roth.lib.util.ReflectionUtil;

public class ServiceReflector
{
	protected Class<?> serviceClass;
	protected String serviceName;
	protected LinkedHashMap<String, MethodReflector> methodReflectorMap = new LinkedHashMap<String, MethodReflector>();
	
	public ServiceReflector(Class<?> serviceClass, String serviceName)
	{
		this.serviceClass = serviceClass;
		this.serviceName = serviceName;
		for(MethodReflector methodReflector : getMethodReflectors(serviceClass))
		{
			methodReflectorMap.put(methodReflector.getName(), methodReflector);
		}
	}
	
	protected static LinkedList<MethodReflector> getMethodReflectors(Class<?> serviceClass)
	{
		LinkedList<MethodReflector> methodReflectors = new LinkedList<MethodReflector>();
		for(java.lang.reflect.Method method : ReflectionUtil.getMethods(serviceClass, HttpService.class))
		{
			Method methodAnnotation = ReflectionUtil.getAnnotation(serviceClass, method, Method.class);
			if(methodAnnotation != null)
			{
				MethodReflector methodReflector = new MethodReflector();
				String methodName = methodAnnotation.name();
				if(methodName == null || methodName.isEmpty())
				{
					methodName = method.getName();
				}
				methodReflector.setName(methodName);
				String context = methodAnnotation.context();
				methodReflector.setContext(context != null && !context.isEmpty() ? context : null);
				methodReflector.setAjax(methodAnnotation.ajax());
				methodReflector.setApi(methodAnnotation.api());
				methodReflector.setAuthenticated(methodAnnotation.authenticated());
				methodReflector.setPost(methodAnnotation.post());
				methodReflector.setGet(methodAnnotation.get());
				methodReflector.setPut(methodAnnotation.put());
				methodReflector.setDelete(methodAnnotation.delete());
				methodReflector.setGzippedInput(methodAnnotation.gzippedInput());
				methodReflector.setMethod(method);
			}
		}
		return methodReflectors;
	}
	
	public Class<?> getServiceClass()
	{
		return serviceClass;
	}
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public LinkedHashMap<String, MethodReflector> getMethodReflectorMap()
	{
		return methodReflectorMap;
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
	
	public ServiceReflector setMethodReflectorMap(LinkedHashMap<String, MethodReflector> methodReflectorMap)
	{
		this.methodReflectorMap = methodReflectorMap;
		return this;
	}
	
}
