package roth.lib.map.xml.tag;

public class CommentTag extends Tag
{
	protected String value;
	
	public CommentTag(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public CommentTag setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public String toString()
	{
		return "<!-- " + value + " -->";
	}
	
}
