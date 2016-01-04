package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaAssert extends JavaCode implements JavaStatement
{
	protected JavaExpression condition;
	protected JavaExpression detail;
	
	public JavaAssert()
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
