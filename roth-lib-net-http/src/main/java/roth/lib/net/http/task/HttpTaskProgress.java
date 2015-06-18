package roth.lib.net.http.task;

import roth.lib.annotation.Property;
import roth.lib.net.http.service.HttpServiceResponse;

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
