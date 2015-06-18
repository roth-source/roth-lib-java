package roth.lib.net.http.task;

import roth.lib.net.http.annotation.Ajax;
import roth.lib.net.http.annotation.Api;
import roth.lib.net.http.annotation.Get;
import roth.lib.net.http.annotation.Post;
import roth.lib.net.http.service.HttpService;
import roth.lib.net.http.service.HttpServiceResponse;
import roth.lib.net.http.util.TaskUtil;

public class HttpTaskService extends HttpService
{
	
	@Get
	@Post
	@Ajax(authenticated = false)
	public HttpTaskProgress getProgress(HttpTaskRequest request)
	{
		HttpTaskProgress response = new HttpTaskProgress();
		response.setProgress(TaskUtil.getProgress(httpServletRequest.getSession(), request.getTaskId()));
		return response;
	}
	
	@Get
	@Post
	@Ajax(authenticated = false)
	public HttpServiceResponse getResponse(HttpTaskRequest request)
	{
		return TaskUtil.getResponse(httpServletRequest.getSession(), request.getTaskId());
	}
	
	@Override
	public boolean isAjaxAuthenticated(Ajax ajax)
	{
		return true;
	}
	
	@Override
	public boolean isApiAuthenticated(Api api)
	{
		return true;
	}
	
	@Override
	public boolean isAuthorized(Object request)
	{
		return true;
	}
	
}
