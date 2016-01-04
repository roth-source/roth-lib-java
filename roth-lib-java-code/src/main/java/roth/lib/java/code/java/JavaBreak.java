package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaBreak extends JavaCode implements JavaStatement
{
	protected String label;
	
	public JavaBreak()
	{
		
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
	
}
