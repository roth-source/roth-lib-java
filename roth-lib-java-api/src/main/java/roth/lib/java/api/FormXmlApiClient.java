package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class FormXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormXmlApiClient(boolean debug)
	{
		super(debug, MapperType.FORM, MapperType.XML);
	}
	
}
