package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class JsonXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public JsonXmlApiClient(boolean debug)
	{
		super(debug, MapperType.JSON, MapperType.XML);
	}
	
}
