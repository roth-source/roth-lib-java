package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class JavaEntity extends JavaAnnotatedCommentedCode implements JavaMember
{
	protected JavaAccess access;
	protected LinkedList<JavaModifier> modifiers = new LinkedList<JavaModifier>();
	protected String name;
	
	protected JavaEntity()
	{
		
	}
	
	public JavaAccess getAccess()
	{
		return access;
	}
	
	public LinkedList<JavaModifier> getModifiers()
	{
		return modifiers;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAccess(JavaAccess access)
	{
		this.access = access;
	}
	
	public void setModifiers(LinkedList<JavaModifier> modifiers)
	{
		this.modifiers.addAll(modifiers);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public boolean isStatic()
	{
		return modifiers.contains(JavaModifier.STATIC);
	}
	
}
