package roth.lib.java.service.task;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.service.HttpServiceResponse;

@Entity
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
