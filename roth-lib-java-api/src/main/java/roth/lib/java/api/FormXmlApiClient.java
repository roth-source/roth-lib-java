package roth.lib.java.api;

import roth.lib.java.map.form.FormReflector;
import roth.lib.java.map.xml.XmlReflector;
import roth.lib.java.type.MimeType;

public abstract class FormXmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public FormXmlApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new FormReflector();
		responseMapperReflector = new XmlReflector();
	}
	
	@Override
	protected MimeType getRequestContentType()
	{
		return MimeType.APPLICATION_X_WWW_FORM_URLENCODED;
	}
	
	@Override
	protected MimeType getResponseContentType()
	{
		return MimeType.APPLICATION_XML;
	}
	
}
