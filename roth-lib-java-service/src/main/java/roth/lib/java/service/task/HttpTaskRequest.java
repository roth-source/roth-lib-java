package roth.lib.java.service.task;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.service.HttpServiceRequest;

@Entity
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
