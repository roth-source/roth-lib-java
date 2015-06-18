package roth.lib.net.http.task;

import roth.lib.annotation.Property;
import roth.lib.net.http.service.HttpServiceRequest;

@SuppressWarnings("serial")
public class HttpTaskRequest extends HttpServiceRequest
{
	@Property(name = "taskId")
	protected String taskId;
	
	public HttpTaskRequest()
	{
		
	}
	
	public String getTaskId()
	{
		return taskId;
	}
	
	public HttpTaskRequest setTaskId(String taskId)
	{
		this.taskId = taskId;
		return this;
	}
	
}
