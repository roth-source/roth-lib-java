package roth.lib.java.api;

import roth.lib.java.map.xml.XmlReflector;

public abstract class XmlApiClient<ApiRequest, ApiResponse> extends ApiClient<ApiRequest, ApiResponse>
{
	
	public XmlApiClient(boolean debug)
	{
		super(debug);
		requestMapperReflector = new XmlReflector();
		responseMapperReflector = new XmlReflector();
	}
	
}
