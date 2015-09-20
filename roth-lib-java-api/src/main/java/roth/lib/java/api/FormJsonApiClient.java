package roth.lib.java.api;

import roth.lib.java.map.form.FormReflector;
import roth.lib.java.map.json.JsonReflector;

public abstract class FormJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormJsonApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new JsonReflector();
	}
	
}
