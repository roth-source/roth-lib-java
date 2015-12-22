package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class XmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public XmlApiClient(boolean debug)
	{
		super(debug, MapperType.XML, MapperType.XML);
	}
	
}
