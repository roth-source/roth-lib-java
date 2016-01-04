package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class JavaComment extends JavaCode implements JavaStatement
{
	protected LinkedList<String> lines = new LinkedList<String>();
	
	protected JavaComment()
	{
		
	}
	
	public LinkedList<String> getLines()
	{
		return lines;
	}
	
	public void setLines(LinkedList<String> lines)
	{
		this.lines = lines;
	}
	
	public String getSpace()
	{
		return "";
	}
	
	public void setSpace(String space)
	{
		
	}
	
}
