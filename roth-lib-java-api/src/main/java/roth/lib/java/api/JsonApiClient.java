package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class JsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public JsonApiClient(boolean debug)
	{
		super(debug, MapperType.JSON, MapperType.JSON);
	}
	
}
