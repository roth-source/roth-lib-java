package roth.lib.service.task;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.service.HttpServiceResponse;

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
