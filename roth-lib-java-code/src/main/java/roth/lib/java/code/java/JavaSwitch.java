package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaSwitch extends JavaCode implements JavaStatement
{
	protected JavaExpression selector;
	protected LinkedList<JavaCase> cases = new LinkedList<JavaCase>();
	
	public JavaSwitch()
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
