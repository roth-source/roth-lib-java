package roth.lib.api.linode.client;

import roth.lib.api.linode.data.request.LinodeRequest;
import roth.lib.api.linode.data.request.get.GetApiKeyRequest;
import roth.lib.api.linode.data.request.get.GetInvoiceEstimateRequest;
import roth.lib.api.linode.data.response.AccountResponse;
import roth.lib.api.linode.data.response.ApiKeyResponse;
import roth.lib.api.linode.data.response.InvoiceEstimateResponse;
import roth.lib.api.linode.data.response.LinodeResponse;
import roth.lib.map.Generic;

public class AccountClient extends LinodeClient
{
	
	public AccountClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public AccountClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<InvoiceEstimateResponse> getInvoiceEstimate(GetInvoiceEstimateRequest invoiceEstimateRequest)
	{
		return post(url(), invoiceEstimateRequest.setApiAction(GET_INVOICE_ESTIMATE), new Generic<LinodeResponse<InvoiceEstimateResponse>>(){});
	}
	
	public LinodeResponse<AccountResponse> getAccountInfo()
	{
		return post(url(), new LinodeRequest().setApiAction(GET_ACCOUNT_INFO), new Generic<LinodeResponse<AccountResponse>>(){});
	}
	
	public LinodeResponse<ApiKeyResponse> getApiKey(GetApiKeyRequest apiKeyRequest)
	{
		return post(url(), apiKeyRequest.setApiAction(GET_API_KEY), new Generic<LinodeResponse<ApiKeyResponse>>(){});
	}
	
}
