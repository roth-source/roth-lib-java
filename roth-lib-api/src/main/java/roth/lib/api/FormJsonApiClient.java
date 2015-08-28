package roth.lib.api;

import roth.lib.map.form.FormReflector;
import roth.lib.map.json.JsonReflector;

public abstract class FormJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormJsonApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new JsonReflector();
	}
	
}
