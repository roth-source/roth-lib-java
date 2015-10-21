package roth.lib.java.api;

import roth.lib.java.map.xml.XmlReflector;
import roth.lib.java.type.MimeType;

public abstract class XmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public XmlApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new XmlReflector();
		responseMapperReflector = new XmlReflector();
	}
	
	@Override
	protected MimeType getRequestContentType()
	{
		return MimeType.APPLICATION_XML;
	}
	
	@Override
	protected MimeType getResponseContentType()
	{
		return MimeType.APPLICATION_XML;
	}
	
}
