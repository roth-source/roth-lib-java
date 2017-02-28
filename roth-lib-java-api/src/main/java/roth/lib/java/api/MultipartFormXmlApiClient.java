package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class MultipartFormXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public MultipartFormXmlApiClient(boolean debug)
	{
		super(debug, MapperType.MULTIPART_FORM, MapperType.XML);
	}
	
}
