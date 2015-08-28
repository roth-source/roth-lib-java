package roth.lib.map.xml.soap;

import roth.lib.annotation.Property;
import roth.lib.map.xml.XmlModel;

@SuppressWarnings("serial")
public class SoapBody<T> extends XmlModel
{
	@Property(name = "soap:Fault")
	protected SoapFault fault;
	
	@Property(name = "entity")
	protected T entity;
	
	public SoapBody()
	{
		
	}
	
	public SoapFault getFault()
	{
		return fault;
	}
	
	public T getEntity()
	{
		return entity;
	}
	
	public SoapBody<T> setFault(SoapFault fault)
	{
		this.fault = fault;
		return this;
	}
	
	public SoapBody<T> setEntity(T entity)
	{
		this.entity = entity;
		return this;
	}
	
}
