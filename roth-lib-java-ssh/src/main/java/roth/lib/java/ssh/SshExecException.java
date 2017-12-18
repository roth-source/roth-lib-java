package roth.lib.java.ssh;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class SshExecException extends RuntimeException
{
	protected int code;
	protected String command;
	protected List<String> lines;
	
	public SshExecException(int code, String command, List<String> lines)
	{
		super(String.join("\n", lines));
		this.code = code;
		this.command = command;
		this.lines = lines;
	}
	
}
