package roth.lib.java.map.xml.tag;

public class DeclarationTag extends Tag
{
	protected String value;
	
	public DeclarationTag(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public DeclarationTag setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public String toString()
	{
		return "<?" + value + "?>";
	}
	
}
