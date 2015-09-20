package roth.lib.java.service.task;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.service.HttpServiceResponse;

@Entity
@SuppressWarnings("serial")
public class HttpTaskProgress extends HttpServiceResponse
{
	@Property(name = "progress")
	protected String progress;
	
	public HttpTaskProgress()
	{
		
	}
	
	public String getProgress()
	{
		return progress;
	}
	
	public HttpTaskProgress setProgress(String progress)
	{
		this.progress = progress;
		return this;
	}
	
}
