package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.THROWS;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaConstructor extends JavaAnnotatedCommentedCode implements JavaMember
{
	protected List<JavaAnnotation> annotations = new List<JavaAnnotation>();
	protected JavaAccess access;
	protected String name;
	protected List<JavaParameter> parameters = new List<JavaParameter>();
	protected List<JavaType> throwTypes = new List<JavaType>();
	protected JavaBlock block;
	
	public JavaConstructor()
	{
		
	}
	
	public List<JavaAnnotation> getAnnotations()
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
	
	public List<JavaParameter> getParameters()
	{
		return parameters;
	}
	
	public List<JavaType> getThrowTypes()
	{
		return throwTypes;
	}
	
	public JavaBlock getBlock()
	{
		return block;
	}
	
	public void setAnnotations(List<JavaAnnotation> annotations)
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
	
	public void setParameters(List<JavaParameter> parameters)
	{
		this.parameters.addAll(parameters);
	}
	
	public void setThrowTypes(List<JavaType> throwTypes)
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
