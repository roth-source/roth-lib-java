package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaSkip extends JavaCode implements JavaStatement
{
	
	public JavaSkip()
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
