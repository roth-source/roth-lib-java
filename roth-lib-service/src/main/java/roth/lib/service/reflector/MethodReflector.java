package roth.lib.service.reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import roth.lib.net.http.HttpMethod;
import roth.lib.service.HttpService;

public class MethodReflector
{
	protected String name;
	protected String context;
	protected boolean ajax;
	protected boolean api;
	protected boolean authenticated;
	protected boolean post;
	protected boolean get;
	protected boolean put;
	protected boolean delete;
	protected boolean gzippedInput;
	protected Method method;
	
	public MethodReflector()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public boolean isAjax()
	{
		return ajax;
	}
	
	public boolean isApi()
	{
		return api;
	}
	
	public boolean isAuthenticated()
	{
		return authenticated;
	}
	
	public boolean isPost()
	{
		return post;
	}
	
	public boolean isGet()
	{
		return get;
	}
	
	public boolean isPut()
	{
		return put;
	}
	
	public boolean isDelete()
	{
		return delete;
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
		return gzippedInput;
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
	
	public MethodReflector setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public MethodReflector setContext(String context)
	{
		this.context = context;
		return this;
	}
	
	public MethodReflector setAjax(boolean ajax)
	{
		this.ajax = ajax;
		return this;
	}
	
	public MethodReflector setApi(boolean api)
	{
		this.api = api;
		return this;
	}
	
	public MethodReflector setAuthenticated(boolean authenticated)
	{
		this.authenticated = authenticated;
		return this;
	}
	
	public MethodReflector setPost(boolean post)
	{
		this.post = post;
		return this;
	}
	
	public MethodReflector setGet(boolean get)
	{
		this.get = get;
		return this;
	}
	
	public MethodReflector setPut(boolean put)
	{
		this.put = put;
		return this;
	}
	
	public MethodReflector setDelete(boolean delete)
	{
		this.delete = delete;
		return this;
	}
	
	public MethodReflector setGzippedInput(boolean gzippedInput)
	{
		this.gzippedInput = gzippedInput;
		return this;
	}
	
	public MethodReflector setMethod(Method method)
	{
		this.method = method;
		return this;
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
