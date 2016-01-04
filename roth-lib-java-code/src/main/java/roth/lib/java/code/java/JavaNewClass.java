package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaNewClass extends JavaCode implements JavaExpression
{
	
	public JavaNewClass()
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
