package roth.lib.java.xml;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class XmlCdata extends XmlModel
{
	@Property(name = "cdata")
	protected String cdata;
	
	public XmlCdata()
	{
		
	}
	
	public String getCdata()
	{
		return cdata;
	}
	
	public XmlCdata setCdata(String cdata)
	{
		this.cdata = cdata;
		return this;
	}
	
}
