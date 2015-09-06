package roth.lib.service.task;

import roth.lib.annotation.Property;
import roth.lib.service.HttpServiceResponse;

@SuppressWarnings("serial")
public class HttpTaskResponse extends HttpServiceResponse
{
	@Property(name = "taskId")
	protected String taskId;
	
	public HttpTaskResponse()
	{
		
	}
	
	public String getTaskId()
	{
		return taskId;
	}
	
	public HttpTaskResponse setTaskId(String taskId)
	{
		this.taskId = taskId;
		return this;
	}
	
}
