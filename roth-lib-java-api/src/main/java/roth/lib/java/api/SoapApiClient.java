package roth.lib.java.api;

import roth.lib.java.http.HttpResponse;
import roth.lib.java.xml.soap.SoapBody;
import roth.lib.java.xml.soap.SoapEnvelope;
import roth.lib.java.xml.soap.SoapException;
import roth.lib.java.xml.soap.SoapFault;

public abstract class SoapApiClient extends XmlApiClient<SoapEnvelope<? extends SoapBody<?>>, SoapEnvelope<? extends SoapBody<?>>>
{
	
	public SoapApiClient(boolean debug)
	{
		super(debug);
	}
	
	@Override
	protected <T extends SoapEnvelope<? extends SoapBody<?>>> void checkResponse(HttpResponse<T> response)
	{
		SoapEnvelope<? extends SoapBody<?>> envelope = response.getEntity();
		if(envelope != null)
		{
			SoapBody<?> body = envelope.getBody();
			if(body != null)
			{
				SoapFault fault = body.getFault();
				if(fault == null)
				{
					checkSoapResponse(body.getEntity());
				}
				else
				{
					throw new SoapException(fault);
				}
			}
			else
			{
				throw new SoapException("soap body not found");
			}
		}
		else
		{
			throw new SoapException("soap envelope not found");
		}
	}
	
	public void checkSoapResponse(Object entity)
	{
		
	}
	
}
