package roth.lib.java.code.java;

import java.io.Serializable;

import roth.lib.java.Characters;

@SuppressWarnings("serial")
public abstract class JavaCode implements Characters, Serializable
{
	
	protected JavaCode()
	{
		
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	public abstract String toSource(int tabs);
	
	public String indent(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < tabs; i++)
		{
			builder.append(TAB);
		}
		return builder.toString();
	}
	
}
