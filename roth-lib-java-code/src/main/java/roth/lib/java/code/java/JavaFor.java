package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaFor extends JavaCode implements JavaStatement
{
	protected LinkedList<JavaStatement> initializers = new LinkedList<JavaStatement>();
	protected JavaExpression condition;
	protected LinkedList<JavaExpressionStatement> steps = new LinkedList<JavaExpressionStatement>();
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
