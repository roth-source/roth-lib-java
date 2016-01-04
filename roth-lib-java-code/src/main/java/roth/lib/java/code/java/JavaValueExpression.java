package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaValueExpression extends JavaCode implements JavaExpression
{
	protected StringBuilder builder = new StringBuilder();
	
	public JavaValueExpression()
	{
		
	}
	
	public StringBuilder getBuilder()
	{
		return builder;
	}
	
	public String getValue()
	{
		return builder.toString();
	}
	
	@Override
	public String toSource(int tabs)
	{
		return indent(tabs) + getValue();
	}
	
}
