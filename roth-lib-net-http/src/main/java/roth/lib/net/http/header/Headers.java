package roth.lib.net.http.header;

import java.util.Collection;
import java.util.LinkedHashMap;

import roth.lib.net.http.HttpHeader;

public class Headers
{
	protected LinkedHashMap<String, Object> headerMap = new LinkedHashMap<String, Object>();
	
	protected Headers()
	{
		
	}
	
	public LinkedHashMap<String, Object> getHeaderMap()
	{
		return headerMap;
	}
	
	public Headers setHeader(String name, String value)
	{
		headerMap.put(name, value);
		return this;
	}
	
	public Headers setHeader(String name, Collection<String> values)
	{
		headerMap.put(name, values);
		return this;
	}
	
	public Headers setHeaders(HttpHeader... headers)
	{
		if(headers != null)
		{
			for(HttpHeader header : headers)
			{
				headerMap.put(header.getName(), header.getValue());
			}
		}
		return this;
	}
	
	@Override
	public String toString()
	{
		return HeaderMapper.get().serialize(this);
	}
	
}
