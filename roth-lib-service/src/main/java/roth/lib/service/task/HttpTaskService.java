package roth.lib.service.task;

import roth.lib.service.HttpService;
import roth.lib.service.HttpServiceResponse;
import roth.lib.service.annotation.Method;
import roth.lib.service.annotation.Service;
import roth.lib.service.util.TaskUtil;

@Service(name = "task")
public class HttpTaskService extends HttpService
{
	
	@Method(name = "getProgress", ajax = true, api = false, authenticated = false)
	public HttpTaskProgress getProgress(HttpTaskRequest request)
	{
		HttpTaskProgress response = new HttpTaskProgress();
		response.setProgress(TaskUtil.getProgress(httpServletRequest.getSession(), request.getTaskId()));
		return response;
	}
	
	@Method(name = "getResponse", ajax = true, api = false, authenticated = false)
	public HttpServiceResponse getResponse(HttpTaskRequest request)
	{
		return TaskUtil.getResponse(httpServletRequest.getSession(), request.getTaskId());
	}
	
	@Override
	public boolean isAuthorized(Object request)
	{
		return true;
	}
	
}
