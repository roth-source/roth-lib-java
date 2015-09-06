package roth.lib.service.endpoint;

public class HttpError
{
	protected HttpErrorType errorType;
	protected String context;
	
	public HttpError(HttpErrorType errorType)
	{
		this.errorType = errorType;
	}
	
	public HttpError(HttpErrorType errorType, String context)
	{
		this.errorType = errorType;
		this.context = context;
	}
	
	public HttpErrorType getErrorType()
	{
		return errorType;
	}
	
	public String getContext()
	{
		return context;
	}
	
}
