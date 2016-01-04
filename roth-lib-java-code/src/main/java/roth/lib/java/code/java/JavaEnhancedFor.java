package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaEnhancedFor extends JavaCode implements JavaStatement
{
	protected JavaParameter parameter;
	protected JavaExpression expression;
	protected JavaBlock block;
	
	public JavaEnhancedFor()
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
