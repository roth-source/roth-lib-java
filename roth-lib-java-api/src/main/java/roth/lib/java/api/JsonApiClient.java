package roth.lib.java.api;

import roth.lib.java.map.json.JsonReflector;
import roth.lib.java.type.MimeType;

public abstract class JsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public JsonApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new JsonReflector();
		responseMapperReflector = new JsonReflector();
	}
	
	@Override
	protected MimeType getRequestContentType()
	{
		return MimeType.APPLICATION_JSON;
	}
	
	@Override
	protected MimeType getResponseContentType()
	{
		return MimeType.APPLICATION_JSON;
	}
	
}
