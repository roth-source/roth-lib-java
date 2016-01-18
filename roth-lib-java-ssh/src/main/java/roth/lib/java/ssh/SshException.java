package roth.lib.java.ssh;

@SuppressWarnings("serial")
public class SshException extends RuntimeException
{
	
	public SshException(String message)
	{
		super(message);
	}
	
	public SshException(Throwable cause)
	{
		super(cause);
	}
	
	public SshException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
