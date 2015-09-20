package roth.lib.java.api;

import roth.lib.java.map.form.FormReflector;
import roth.lib.java.map.xml.XmlReflector;

public abstract class FormXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormXmlApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new XmlReflector();
	}
	
}
