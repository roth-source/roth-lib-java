package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaContinue extends JavaCode implements JavaStatement
{
	protected String label;
	
	public JavaContinue()
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
