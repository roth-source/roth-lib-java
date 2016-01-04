package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaBlock extends JavaCode implements JavaStatement
{
	protected LinkedList<JavaStatement> statements = new LinkedList<JavaStatement>();
	
	public JavaBlock()
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
		StringBuilder builder = new StringBuilder();
		String indent = indent(tabs);
		String statementIndent = indent(tabs + 1);
		builder.append(indent);
		builder.append(LEFT_BRACE);
		builder.append(NEW_LINE);
		if(!statements.isEmpty())
		{
			
		}
		else
		{
			builder.append(statementIndent);
			builder.append(NEW_LINE);
		}
		builder.append(indent);
		builder.append(RIGHT_BRACE);
		return builder.toString();
	}
	
}
