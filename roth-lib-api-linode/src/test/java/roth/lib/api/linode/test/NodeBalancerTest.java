package roth.lib.api.linode.test;

import roth.lib.api.linode.data.request.create.CreateNodeBalancerRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeBalancerRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeBalancerRequest;

public class NodeBalancerTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getNodeBalancers();
		//createNodeBalancer();
		//updateNodeBalancer();
		//deleteNodeBalancer();
		
	}
	
	public static void getNodeBalancers()
	{
		clientFactory.getNodeBalancerClient().getNodeBalancers();
	}
	
	public static void createNodeBalancer()
	{
		clientFactory.getNodeBalancerClient().createNodeBalancer(new CreateNodeBalancerRequest(2));
	}
	
	public static void updateNodeBalancer()
	{
		clientFactory.getNodeBalancerClient().updateNodeBalancer((UpdateNodeBalancerRequest) new UpdateNodeBalancerRequest(5987).setLabel("test"));
	}
	
	public static void deleteNodeBalancer()
	{
		clientFactory.getNodeBalancerClient().deleteNodeBalancer(new DeleteNodeBalancerRequest(5987));
	}
	
}
