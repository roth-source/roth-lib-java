package roth.lib.java.code.java.unused;

import roth.lib.java.lang.List;

import roth.lib.java.code.java.JavaCode;
import roth.lib.java.code.java.JavaExpression;
import roth.lib.java.code.java.JavaGeneric;

@SuppressWarnings("serial")
public class MethodInvocation extends JavaCode implements JavaExpression
{
	public String selected;
	public List<JavaGeneric> generics = new List<JavaGeneric>();
	public String method;
	public List<JavaExpression> arguments = new List<JavaExpression>();
	
	public MethodInvocation()
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
