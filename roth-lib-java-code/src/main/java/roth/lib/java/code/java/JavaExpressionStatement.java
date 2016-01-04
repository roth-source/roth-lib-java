package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaExpressionStatement extends JavaCode implements JavaStatement
{
	protected JavaExpression expression;
	
	public JavaExpressionStatement()
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
