package roth.lib.java.code.java.unused;

import java.util.LinkedList;

import roth.lib.java.code.java.JavaCode;
import roth.lib.java.code.java.JavaExpression;
import roth.lib.java.code.java.JavaGeneric;

@SuppressWarnings("serial")
public class MethodInvocation extends JavaCode implements JavaExpression
{
	public String selected;
	public LinkedList<JavaGeneric> generics = new LinkedList<JavaGeneric>();
	public String method;
	public LinkedList<JavaExpression> arguments = new LinkedList<JavaExpression>();
	
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
