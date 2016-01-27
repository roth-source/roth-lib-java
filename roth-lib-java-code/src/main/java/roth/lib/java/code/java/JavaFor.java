package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaFor extends JavaCode implements JavaStatement
{
	protected List<JavaStatement> initializers = new List<JavaStatement>();
	protected JavaExpression condition;
	protected List<JavaExpressionStatement> steps = new List<JavaExpressionStatement>();
	protected JavaBlock block;
	
	public JavaFor()
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
