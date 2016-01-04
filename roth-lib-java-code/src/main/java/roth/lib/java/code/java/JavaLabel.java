package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaLabel extends JavaCode implements JavaStatement
{
	protected String name;
	protected JavaBlock block;
	
	public JavaLabel()
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
