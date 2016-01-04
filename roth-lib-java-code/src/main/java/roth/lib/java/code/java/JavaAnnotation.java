package roth.lib.java.code.java;


@SuppressWarnings("serial")
public class JavaAnnotation extends JavaCode implements JavaExpression
{
	protected JavaType type;
	protected JavaExpression arguments;
	
	public JavaAnnotation()
	{
		
	}
	
	public JavaType getType()
	{
		return type;
	}
	
	public void setType(JavaType type)
	{
		this.type = type;
	}
	
	public JavaExpression getArguments()
	{
		return arguments;
	}
	
	public void setArguments(JavaExpression arguments)
	{
		this.arguments = arguments;
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("@");
		builder.append(type);
		if(arguments != null)
		{
			builder.append(LEFT_PAREN);
			builder.append(arguments);
			builder.append(RIGHT_PAREN);
		}
		return builder.toString();
	}
	
}
