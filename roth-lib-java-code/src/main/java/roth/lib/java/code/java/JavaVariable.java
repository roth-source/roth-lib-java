package roth.lib.java.code.java;


@SuppressWarnings("serial")
public class JavaVariable extends JavaCode implements JavaStatement
{
	protected boolean _final;
	protected JavaType type;
	protected String name;
	protected JavaExpression initializer;
	
	public JavaVariable()
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
