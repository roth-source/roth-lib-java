package roth.lib.java.api.linode.domain;

import java.util.LinkedList;

import roth.lib.java.Generic;
import roth.lib.java.api.linode.LinodeClient;
import roth.lib.java.api.linode.data.model.Domain;
import roth.lib.java.api.linode.data.model.DomainResource;
import roth.lib.java.api.linode.data.request.create.CreateDomainRequest;
import roth.lib.java.api.linode.data.request.create.CreateDomainResourceRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteDomainRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteDomainResourceRequest;
import roth.lib.java.api.linode.data.request.get.GetDomainRequest;
import roth.lib.java.api.linode.data.request.get.GetDomainResourceRequest;
import roth.lib.java.api.linode.data.request.update.UpdateDomainRequest;
import roth.lib.java.api.linode.data.request.update.UpdateDomainResourceRequest;
import roth.lib.java.api.linode.data.response.DomainIdResponse;
import roth.lib.java.api.linode.data.response.DomainResourceIdResponse;
import roth.lib.java.api.linode.data.response.LinodeResponse;

public class DomainClient extends LinodeClient
{
	
	public DomainClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public DomainClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<LinkedList<Domain>> getDomains()
	{
		return getDomain(new GetDomainRequest());
	}
	
	public LinodeResponse<LinkedList<Domain>> getDomain(GetDomainRequest getDomainRequest)
	{
		return post(url(), getDomainRequest.setApiAction(GET_DOMAIN), new Generic<LinodeResponse<LinkedList<Domain>>>(){});
	}
	
	public LinodeResponse<DomainIdResponse> createDomain(CreateDomainRequest createDomainRequest)
	{
		return post(url(), createDomainRequest.setApiAction(CREATE_DOMAIN), new Generic<LinodeResponse<DomainIdResponse>>(){});
	}
	
	public LinodeResponse<DomainIdResponse> updateDomain(UpdateDomainRequest updateDomainRequest)
	{
		return post(url(), updateDomainRequest.setApiAction(UPDATE_DOMAIN), new Generic<LinodeResponse<DomainIdResponse>>(){});
	}
	
	public LinodeResponse<DomainIdResponse> deleteDomain(DeleteDomainRequest deleteDomainRequest)
	{
		return post(url(), deleteDomainRequest.setApiAction(DELETE_DOMAIN), new Generic<LinodeResponse<DomainIdResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<DomainResource>> getDomainResource(GetDomainResourceRequest getDomainResourceRequest)
	{
		return post(url(), getDomainResourceRequest.setApiAction(GET_DOMAIN_RESOURCE), new Generic<LinodeResponse<LinkedList<DomainResource>>>(){});
	}
	
	public LinodeResponse<DomainResourceIdResponse> createDomainResource(CreateDomainResourceRequest createDomainResourceRequest)
	{
		return post(url(), createDomainResourceRequest.setApiAction(CREATE_DOMAIN_RESOURCE), new Generic<LinodeResponse<DomainResourceIdResponse>>(){});
	}
	
	public LinodeResponse<DomainResourceIdResponse> updateDomainResource(UpdateDomainResourceRequest updateDomainResourceRequest)
	{
		return post(url(), updateDomainResourceRequest.setApiAction(UPDATE_DOMAIN_RESOURCE), new Generic<LinodeResponse<DomainResourceIdResponse>>(){});
	}
	
	public LinodeResponse<DomainResourceIdResponse> deleteDomainResource(DeleteDomainResourceRequest deleteDomainResourceRequest)
	{
		return post(url(), deleteDomainResourceRequest.setApiAction(DELETE_DOMAIN_RESOURCE), new Generic<LinodeResponse<DomainResourceIdResponse>>(){});
	}
	
}
