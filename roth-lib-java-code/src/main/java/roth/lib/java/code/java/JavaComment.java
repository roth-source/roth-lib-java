package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class JavaComment extends JavaCode implements JavaStatement
{
	protected List<String> lines = new List<String>();
	
	protected JavaComment()
	{
		
	}
	
	public List<String> getLines()
	{
		return lines;
	}
	
	public void setLines(List<String> lines)
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
