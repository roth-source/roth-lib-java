package roth.lib.java.map.form;

@SuppressWarnings("serial")
public class FormException extends RuntimeException
{
	
	public FormException(String message)
	{
		super(message);
	}
	
	public FormException(Throwable cause)
	{
		super(cause);
	}
	
	public FormException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
