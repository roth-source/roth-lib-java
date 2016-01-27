package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaField extends JavaAnnotatedCommentedCode implements JavaMember
{
	protected JavaAccess access;
	protected List<JavaModifier> modifiers = new List<JavaModifier>();
	protected JavaType type;
	protected String name;
	protected String assignment;
	protected JavaExpression initializer;
	
	public JavaField()
	{
		
	}
	
	public JavaAccess getAccess()
	{
		return access;
	}
	
	public List<JavaModifier> getModifiers()
	{
		return modifiers;
	}
	
	public JavaType getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAssignment()
	{
		return assignment;
	}
	
	public JavaExpression getInitializer()
	{
		return initializer;
	}
	
	public void setAccess(JavaAccess access)
	{
		this.access = access;
	}
	
	public void setModifiers(List<JavaModifier> modifiers)
	{
		this.modifiers.addAll(modifiers);
	}
	
	public void setType(JavaType type)
	{
		this.type = type;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAssignment(String assignment)
	{
		this.assignment = assignment;
	}
	
	public void setInitializer(JavaExpression initializer)
	{
		this.initializer = initializer;
	}
	
	@Override
	public boolean isStatic()
	{
		return modifiers.contains(JavaModifier.STATIC);
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
		String indent = indent(tabs);
		builder.append(super.toSource(tabs));
		builder.append(indent);
		if(access != null)
		{
			builder.append(access);
			builder.append(SPACE);
		}
		for(JavaModifier modifier : modifiers)
		{
			builder.append(modifier);
			builder.append(SPACE);
		}
		builder.append(type);
		builder.append(SPACE);
		builder.append(name);
		if(initializer != null)
		{
			if(assignment != null)
			{
				builder.append(assignment);
			}
			else
			{
				builder.append(SPACE);
				builder.append(EQUAL);
				builder.append(SPACE);
			}
			builder.append(initializer);
		}
		builder.append(SEMI_COLON);
		return builder.toString();
	}
	
}
