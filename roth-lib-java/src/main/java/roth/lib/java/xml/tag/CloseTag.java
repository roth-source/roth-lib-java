package roth.lib.java.xml.tag;

public class CloseTag extends Tag
{
	protected String name;
	
	public CloseTag(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public CloseTag setName(String name)
	{
		this.name = name;
		return this;
	}
	
	@Override
	public String toString()
	{
		return "</" + name + ">";
	}
	
}
