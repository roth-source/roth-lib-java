package roth.lib.java.api;

import roth.lib.java.map.json.JsonReflector;

public abstract class JsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public JsonApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new JsonReflector();
		responseMapperReflector = new JsonReflector();
	}
	
}
