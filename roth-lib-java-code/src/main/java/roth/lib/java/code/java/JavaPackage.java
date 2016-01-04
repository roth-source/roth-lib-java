package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.PACKAGE;

@SuppressWarnings("serial")
public class JavaPackage extends JavaCommentedCode
{
	protected String name;
	
	public JavaPackage()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
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
		builder.append(PACKAGE);
		builder.append(SPACE);
		builder.append(name);
		builder.append(SEMI_COLON);
		return builder.toString();
	}
	
}
