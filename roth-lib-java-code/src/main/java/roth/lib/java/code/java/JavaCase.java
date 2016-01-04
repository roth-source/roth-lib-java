package roth.lib.java.code.java;


@SuppressWarnings("serial")
public class JavaCase extends JavaCode implements JavaStatement
{
	protected JavaExpression value;
	protected JavaBlock block;
	
	public JavaCase()
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
