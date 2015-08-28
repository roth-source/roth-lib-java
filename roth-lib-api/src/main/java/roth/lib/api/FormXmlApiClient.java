package roth.lib.api;

import roth.lib.map.form.FormReflector;
import roth.lib.map.xml.XmlReflector;

public abstract class FormXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormXmlApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new XmlReflector();
	}
	
}
