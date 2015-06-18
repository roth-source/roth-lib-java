package roth.lib.api.linode.test;

import roth.lib.api.linode.data.request.action.BootNodeRequest;
import roth.lib.api.linode.data.request.action.CloneNodeRequest;
import roth.lib.api.linode.data.request.action.RebootNodeRequest;
import roth.lib.api.linode.data.request.action.ResizeNodeRequest;
import roth.lib.api.linode.data.request.action.ShutdownNodeRequest;
import roth.lib.api.linode.data.request.create.CreateNodeRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeRequest;

public class NodeTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getNodes();
		//createNode();
		//updateNode();
		//cloneNode();
		//shutdownNode();
		//bootNode();
		//rebootNode();
		//resizeNode();
		//deleteNode();
	}
	
	public static void getNodes()
	{
		clientFactory.getNodeClient().getNodes();
	}
	
	public static void createNode()
	{
		clientFactory.getNodeClient().createNode(new CreateNodeRequest(2, 1));
	}
	
	public static void updateNode()
	{
		clientFactory.getNodeClient().updateNode(new UpdateNodeRequest(575385));
	}
	
	public static void cloneNode()
	{
		clientFactory.getNodeClient().cloneNode(new CloneNodeRequest(575385, 3, 1));
	}
	
	public static void shutdownNode()
	{
		clientFactory.getNodeClient().shutdownNode(new ShutdownNodeRequest(575385));
	}
	
	public static void bootNode()
	{
		clientFactory.getNodeClient().bootNode(new BootNodeRequest(575385));
	}
	
	public static void rebootNode()
	{
		clientFactory.getNodeClient().rebootNode(new RebootNodeRequest(575385));
	}
	
	public static void resizeNode()
	{
		clientFactory.getNodeClient().resizeNode(new ResizeNodeRequest(575385, 1));
	}
	
	public static void deleteNode()
	{
		clientFactory.getNodeClient().deleteNode(new DeleteNodeRequest(575385));
	}
	
}
