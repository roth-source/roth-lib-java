package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaTry extends JavaCode implements JavaStatement
{
	protected LinkedList<JavaVariable> resources = new LinkedList<JavaVariable>();
	protected JavaBlock block;
	protected LinkedList<JavaCatch> catches = new LinkedList<JavaCatch>();
	protected JavaBlock finallyBlock;
	
	public JavaTry()
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
