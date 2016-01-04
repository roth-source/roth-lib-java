package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.THROWS;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaConstructor extends JavaAnnotatedCommentedCode implements JavaMember
{
	protected LinkedList<JavaAnnotation> annotations = new LinkedList<JavaAnnotation>();
	protected JavaAccess access;
	protected String name;
	protected LinkedList<JavaParameter> parameters = new LinkedList<JavaParameter>();
	protected LinkedList<JavaType> throwTypes = new LinkedList<JavaType>();
	protected JavaBlock block;
	
	public JavaConstructor()
	{
		
	}
	
	public LinkedList<JavaAnnotation> getAnnotations()
	{
		return annotations;
	}
	
	public JavaAccess getAccess()
	{
		return access;
	}
	
	public String getName()
	{
		return name;
	}
	
	public LinkedList<JavaParameter> getParameters()
	{
		return parameters;
	}
	
	public LinkedList<JavaType> getThrowTypes()
	{
		return throwTypes;
	}
	
	public JavaBlock getBlock()
	{
		return block;
	}
	
	public void setAnnotations(LinkedList<JavaAnnotation> annotations)
	{
		this.annotations.addAll(annotations);
	}
	
	public void setAccess(JavaAccess access)
	{
		this.access = access;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setParameters(LinkedList<JavaParameter> parameters)
	{
		this.parameters.addAll(parameters);
	}
	
	public void setThrowTypes(LinkedList<JavaType> throwTypes)
	{
		this.throwTypes.addAll(throwTypes);
	}
	
	public void setBlock(JavaBlock block)
	{
		this.block = block;
	}
	
	@Override
	public boolean isStatic()
	{
		return false;
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
		builder.append(indent(tabs));
		if(access != null)
		{
			builder.append(access);
			builder.append(SPACE);
		}
		builder.append(name);
		builder.append(LEFT_PAREN);
		{
			String seperator = BLANK;
			for(JavaParameter parameter : parameters)
			{
				builder.append(seperator);
				builder.append(parameter);
				seperator = COMMA_SEPERATOR;
			}
		}
		builder.append(RIGHT_PAREN);
		if(!throwTypes.isEmpty())
		{
			builder.append(SPACE);
			builder.append(THROWS);
			builder.append(SPACE);
			String seperator = BLANK;
			for(JavaType throwType : throwTypes)
			{
				builder.append(seperator);
				builder.append(throwType);
				seperator = COMMA_SEPERATOR;
			}
		}
		builder.append(block.toSource(tabs));
		return builder.toString();
	}
	
}
