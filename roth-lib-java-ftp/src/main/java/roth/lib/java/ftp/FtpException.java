package roth.lib.java.ftp;

@SuppressWarnings("serial")
public class FtpException extends RuntimeException
{
	
	public FtpException(String message)
	{
		super(message);
	}
	
	public FtpException(Throwable cause)
	{
		super(cause);
	}
	
	public FtpException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
