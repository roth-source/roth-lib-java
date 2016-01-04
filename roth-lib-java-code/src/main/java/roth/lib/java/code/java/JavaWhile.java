package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaWhile extends JavaCode implements JavaStatement
{
	protected JavaExpression condition;
	protected JavaBlock block;
	
	public JavaWhile()
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
