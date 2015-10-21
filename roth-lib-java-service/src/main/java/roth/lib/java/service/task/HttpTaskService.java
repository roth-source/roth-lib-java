package roth.lib.java.service.task;

import roth.lib.java.service.HttpService;
import roth.lib.java.service.HttpServiceResponse;
import roth.lib.java.service.annotation.Service;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.service.util.TaskUtil;

@Service(name = "task")
public class HttpTaskService extends HttpService
{
	
	@ServiceMethod(authenticated = false)
	public HttpTaskProgress getProgress(HttpTaskRequest request)
	{
		HttpTaskProgress response = new HttpTaskProgress();
		response.setProgress(TaskUtil.getProgress(httpServletRequest.getSession(), request.getTaskId()));
		return response;
	}
	
	@ServiceMethod(authenticated = false)
	public HttpServiceResponse getResponse(HttpTaskRequest request)
	{
		return TaskUtil.getResponse(httpServletRequest.getSession(), request.getTaskId());
	}
	
	@Override
	public boolean isAjaxAuthenticated(MethodReflector methodReflector)
	{
		return true;
	}
	
	@Override
	public boolean isApiAuthenticated(MethodReflector methodReflector)
	{
		return true;
	}
	
	@Override
	public boolean isAuthorized(MethodReflector methodReflector, Object request)
	{
		return true;
	}
	
	
}
