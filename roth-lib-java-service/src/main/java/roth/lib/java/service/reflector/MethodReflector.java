package roth.lib.java.service.reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import roth.lib.java.http.HttpMethod;
import roth.lib.java.service.HttpService;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.util.AnnotationUtil;

public class MethodReflector
{
	protected ServiceReflector serviceReflector;
	protected Method method;
	protected ServiceMethod serviceMethod;
	
	public MethodReflector(ServiceReflector serviceReflector, Method method, ServiceMethod serviceMethod)
	{
		this.serviceReflector = serviceReflector;
		this.method = method;
		this.serviceMethod = serviceMethod;
	}
	
	public String getMethodName()
	{
		String name = getName();
		if(name == null)
		{
			name = method.getName();
		}
		return name;
	}
	
	public String getName()
	{
		return AnnotationUtil.validate(serviceMethod.name());
	}
	
	public String getContext()
	{
		return  AnnotationUtil.validate(serviceMethod.context());
	}
	
	public boolean isAjax()
	{
		return serviceMethod.ajax();
	}
	
	public boolean isApi()
	{
		return serviceMethod.api();
	}
	
	public boolean isAuthenticated()
	{
		return serviceMethod.authenticated();
	}
	
	public boolean isPost()
	{
		return serviceMethod.post();
	}
	
	public boolean isGet()
	{
		return serviceMethod.get();
	}
	
	public boolean isPut()
	{
		return serviceMethod.put();
	}
	
	public boolean isDelete()
	{
		return serviceMethod.delete();
	}
	
	public boolean isHttpMethodImplemented(HttpMethod httpMethod)
	{
		boolean implemented = false;
		switch(httpMethod)
		{
			case POST:
			{
				implemented = isPost();
				break;
			}
			case GET:
			{
				implemented = isGet();
				break;
			}
			case PUT:
			{
				implemented = isPut();
				break;
			}
			case DELETE:
			{
				implemented = isDelete();
				break;
			}
			case HEAD:
			case OPTIONS:
			default:
			{
				break;
			}
		}
		return implemented;
	}
	
	public boolean isGzippedInput()
	{
		return serviceMethod.gzippedInput();
	}
	
	public Method getMethod()
	{
		return method;
	}
	
	public Parameter getParameter()
	{
		Parameter parameter = null;
		Parameter[] parameters = method.getParameters();
		if(parameters != null && parameters.length > 0)
		{
			parameter = parameters[0];
		}
		return parameter;
	}
	
	public Object invoke(HttpService service, Object methodRequest) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Object methodRespnose = null;
		Parameter parameter = getParameter();
		if(parameter != null)
		{
			methodRespnose = method.invoke(service, methodRequest);
		}
		else
		{
			methodRespnose = method.invoke(service);
		}
		return methodRespnose;
	}
	
}
