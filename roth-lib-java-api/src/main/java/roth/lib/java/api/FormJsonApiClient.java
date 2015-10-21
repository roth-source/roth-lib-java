package roth.lib.java.api;

import roth.lib.java.map.form.FormReflector;
import roth.lib.java.map.json.JsonReflector;
import roth.lib.java.type.MimeType;

public abstract class FormJsonApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormJsonApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new JsonReflector();
	}
	
	@Override
	protected MimeType getRequestContentType()
	{
		return MimeType.APPLICATION_X_WWW_FORM_URLENCODED;
	}
	
	@Override
	protected MimeType getResponseContentType()
	{
		return MimeType.APPLICATION_JSON;
	}
	
}
