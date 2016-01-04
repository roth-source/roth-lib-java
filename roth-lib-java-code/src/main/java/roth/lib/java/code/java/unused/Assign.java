package roth.lib.java.code.java.unused;

import roth.lib.java.code.java.JavaCode;
import roth.lib.java.code.java.JavaExpression;

@SuppressWarnings("serial")
public class Assign extends JavaCode implements JavaExpression
{
	public String preOperator;
	public JavaExpression variable;
	public String operator;
	public JavaExpression assignment;
	
	public Assign()
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
