package roth.lib.java.template;

@SuppressWarnings("serial")
public class TemplateException extends RuntimeException
{
	
	public TemplateException(String message)
	{
		super(message);
	}
	
	public TemplateException(Throwable cause)
	{
		super(cause);
	}
	
	public TemplateException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
