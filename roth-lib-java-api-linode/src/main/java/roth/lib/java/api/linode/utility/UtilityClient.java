package roth.lib.java.api.linode.utility;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.Generic;
import roth.lib.java.api.linode.LinodeClient;
import roth.lib.java.api.linode.data.model.Datacenter;
import roth.lib.java.api.linode.data.model.Distribution;
import roth.lib.java.api.linode.data.model.Kernel;
import roth.lib.java.api.linode.data.model.NodeBalancerPlan;
import roth.lib.java.api.linode.data.model.Plan;
import roth.lib.java.api.linode.data.model.StackScript;
import roth.lib.java.api.linode.data.request.LinodeRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailDistributionRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailKernelRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailPlanRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailStackScriptRequest;
import roth.lib.java.api.linode.data.response.ApiSpecResponse;
import roth.lib.java.api.linode.data.response.LinodeResponse;

public class UtilityClient extends LinodeClient
{
	
	public UtilityClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public UtilityClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<ApiSpecResponse> getApiSpec()
	{
		return post(url(), new LinodeRequest().setApiAction(GET_API_SPEC), new Generic<LinodeResponse<ApiSpecResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<Datacenter>> getAvailDatacenters()
	{
		return post(url(), new LinodeRequest().setApiAction(GET_AVAIL_DATA_CENTERS), new Generic<LinodeResponse<LinkedList<Datacenter>>>(){});
	}
	
	public LinodeResponse<LinkedList<Distribution>> getAvailDistributions()
	{
		return getAvailDistribution(new GetAvailDistributionRequest());
	}
	
	public LinodeResponse<LinkedList<Distribution>> getAvailDistribution(GetAvailDistributionRequest distributionRequest)
	{
		return post(url(), distributionRequest.setApiAction(GET_AVAIL_DISTRIBUTIONS), new Generic<LinodeResponse<LinkedList<Distribution>>>(){});
	}
	
	public LinodeResponse<LinkedList<Kernel>> getAvailKernels()
	{
		return getAvailKernel(new GetAvailKernelRequest());
	}
	
	public LinodeResponse<LinkedList<Kernel>> getAvailKernel(GetAvailKernelRequest kernelRequest)
	{
		return post(url(), kernelRequest.setApiAction(GET_AVAIL_KERNELS), new Generic<LinodeResponse<LinkedList<Kernel>>>(){});
	}
	
	public LinodeResponse<LinkedList<Plan>> getAvailPlans()
	{
		return getAvailPlan(new GetAvailPlanRequest());
	}
	
	public LinodeResponse<LinkedList<Plan>> getAvailPlan(GetAvailPlanRequest planRequest)
	{
		return post(url(), planRequest.setApiAction(GET_AVAIL_PLANS), new Generic<LinodeResponse<LinkedList<Plan>>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeBalancerPlan>> getAvailNodeBalancers()
	{
		return post(url(), new LinodeRequest().setApiAction(GET_AVAIL_NODE_BALANCERS), new Generic<LinodeResponse<LinkedList<NodeBalancerPlan>>>(){});
	}
	
	public LinodeResponse<LinkedList<StackScript>> getAvailStackScripts()
	{
		return getAvailStackScript(new GetAvailStackScriptRequest());
	}
	
	public LinodeResponse<LinkedList<StackScript>> getAvailStackScript(GetAvailStackScriptRequest stackScriptRequest)
	{
		return post(url(), stackScriptRequest.setApiAction(GET_AVAIL_STACK_SCRIPTS), new Generic<LinodeResponse<LinkedList<StackScript>>>(){});
	}
	
	public LinodeResponse<LinkedHashMap<String, Object>> testEcho(LinodeRequest request)
	{
		return post(url(), request.setApiAction(TEST_ECHO), new Generic<LinodeResponse<LinkedHashMap<String, Object>>>(){});
	}
	
	public LinodeResponse<LinkedHashMap<String, Object>> testFail()
	{
		return post(url(), new LinodeRequest().setApiAction(TEST_FAIL), new Generic<LinodeResponse<LinkedHashMap<String, Object>>>(){});
	}
	
}
