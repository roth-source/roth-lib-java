package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaDoWhile extends JavaCode implements JavaStatement
{
	protected JavaBlock block;
	protected JavaExpression condition;
	
	public JavaDoWhile()
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
