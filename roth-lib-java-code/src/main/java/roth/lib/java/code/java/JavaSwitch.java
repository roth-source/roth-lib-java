package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaSwitch extends JavaCode implements JavaStatement
{
	protected JavaExpression selector;
	protected List<JavaCase> cases = new List<JavaCase>();
	
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
