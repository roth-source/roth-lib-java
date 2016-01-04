package roth.lib.java.code.java.unused;

import roth.lib.java.code.java.JavaCode;

@SuppressWarnings("serial")
public class Snippet extends JavaCode
{
	protected String value;
	
	public Snippet()
	{
		
	}
	
	@Override
	public String toSource(int tabs)
	{
		return value;
	}
	
}
