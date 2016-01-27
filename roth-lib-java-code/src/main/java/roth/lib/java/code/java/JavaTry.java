package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaTry extends JavaCode implements JavaStatement
{
	protected List<JavaVariable> resources = new List<JavaVariable>();
	protected JavaBlock block;
	protected List<JavaCatch> catches = new List<JavaCatch>();
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
