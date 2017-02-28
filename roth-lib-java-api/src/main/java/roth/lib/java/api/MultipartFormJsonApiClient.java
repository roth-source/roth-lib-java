package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class MultipartFormJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public MultipartFormJsonApiClient(boolean debug)
	{
		super(debug, MapperType.MULTIPART_FORM, MapperType.JSON);
	}
	
}
