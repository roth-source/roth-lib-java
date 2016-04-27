package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class XmlJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public XmlJsonApiClient(boolean debug)
	{
		super(debug, MapperType.XML, MapperType.JSON);
	}
	
}
