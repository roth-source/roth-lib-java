package roth.lib.java.api;

import roth.lib.java.mapper.MapperType;

public abstract class FormJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormJsonApiClient(boolean debug)
	{
		super(debug, MapperType.FORM, MapperType.JSON);
	}
	
}
