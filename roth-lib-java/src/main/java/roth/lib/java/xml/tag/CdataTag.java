package roth.lib.java.xml.tag;

public class CdataTag extends Tag
{
	protected String cdata;
	
	public CdataTag(String cdata)
	{
		this.cdata = cdata;
	}
	
	public String getCdata()
	{
		return cdata;
	}
	
	public CdataTag setCdata(String cdata)
	{
		this.cdata = cdata;
		return this;
	}
	
	@Override
	public String toString()
	{
		return "<![CDATA[ " + cdata + " ]]>";
	}
	
}
