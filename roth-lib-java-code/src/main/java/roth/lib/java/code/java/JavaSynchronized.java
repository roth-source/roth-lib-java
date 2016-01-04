package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaSynchronized extends JavaCode implements JavaStatement
{
	protected JavaExpression lock;
	protected JavaBlock block;
	
	public JavaSynchronized()
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
