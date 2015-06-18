package roth.lib.api.linode.test;

import roth.lib.api.linode.data.request.get.GetApiKeyRequest;
import roth.lib.api.linode.data.request.get.GetInvoiceEstimateRequest;
import roth.lib.api.linode.data.type.ModeType;

public class AccountTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getInvoiceEstimate();
		//getAccountInfo();
		//getApiKey();
	}
	
	public static void getInvoiceEstimate()
	{
		clientFactory.getAccountClient().getInvoiceEstimate(new GetInvoiceEstimateRequest(ModeType.LINODE_NEW).setPaymentTerm(12).setPlanId(1));
	}
	
	public static void getAccountInfo()
	{
		clientFactory.getAccountClient().getAccountInfo();
	}
	
	public static void getApiKey()
	{
		clientFactory.getAccountClient().getApiKey(new GetApiKeyRequest("", ""));
	}
	
}
