package roth.lib.api.linode.client;

import java.util.LinkedList;

import roth.lib.Generic;
import roth.lib.api.linode.data.model.Node;
import roth.lib.api.linode.data.model.NodeConfig;
import roth.lib.api.linode.data.model.NodeDisk;
import roth.lib.api.linode.data.model.NodeIp;
import roth.lib.api.linode.data.model.NodeJob;
import roth.lib.api.linode.data.request.action.AddPrivateNodeIpRequest;
import roth.lib.api.linode.data.request.action.AddPublicNodeIpRequest;
import roth.lib.api.linode.data.request.action.BootNodeRequest;
import roth.lib.api.linode.data.request.action.CloneNodeRequest;
import roth.lib.api.linode.data.request.action.DuplicateNodeDiskRequest;
import roth.lib.api.linode.data.request.action.RebootNodeRequest;
import roth.lib.api.linode.data.request.action.ResizeNodeDiskRequest;
import roth.lib.api.linode.data.request.action.ResizeNodeRequest;
import roth.lib.api.linode.data.request.action.SetRdnsNodeIpRequest;
import roth.lib.api.linode.data.request.action.ShutdownNodeRequest;
import roth.lib.api.linode.data.request.action.SwapNodeIpRequest;
import roth.lib.api.linode.data.request.create.CreateNodeConfigRequest;
import roth.lib.api.linode.data.request.create.CreateNodeDiskFromDistRequest;
import roth.lib.api.linode.data.request.create.CreateNodeDiskFromScriptRequest;
import roth.lib.api.linode.data.request.create.CreateNodeDiskRequest;
import roth.lib.api.linode.data.request.create.CreateNodeRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeConfigRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeDiskRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeRequest;
import roth.lib.api.linode.data.request.get.GetNodeConfigRequest;
import roth.lib.api.linode.data.request.get.GetNodeDiskRequest;
import roth.lib.api.linode.data.request.get.GetNodeIpRequest;
import roth.lib.api.linode.data.request.get.GetNodeJobRequest;
import roth.lib.api.linode.data.request.get.GetNodeRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeConfigRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeDiskRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeRequest;
import roth.lib.api.linode.data.response.LinodeResponse;
import roth.lib.api.linode.data.response.NodeConfigIdResponse;
import roth.lib.api.linode.data.response.NodeDiskIdResponse;
import roth.lib.api.linode.data.response.NodeDiskJobIdResponse;
import roth.lib.api.linode.data.response.NodeIdResponse;
import roth.lib.api.linode.data.response.NodeIpResponse;
import roth.lib.api.linode.data.response.NodeJobIdResponse;

public class NodeClient extends LinodeClient
{
	
	public NodeClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public NodeClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<LinkedList<Node>> getNodes()
	{
		return getNode(new GetNodeRequest());
	}
	
	public LinodeResponse<LinkedList<Node>> getNode(GetNodeRequest getNodeRequest)
	{
		return post(url(), getNodeRequest.setApiAction(GET_NODE), new Generic<LinodeResponse<LinkedList<Node>>>(){});
	}
	
	public LinodeResponse<NodeIdResponse> createNode(CreateNodeRequest createNodeRequest)
	{
		return post(url(), createNodeRequest.setApiAction(CREATE_NODE), new Generic<LinodeResponse<NodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeIdResponse> updateNode(UpdateNodeRequest updateNodeRequest)
	{
		return post(url(), updateNodeRequest.setApiAction(UPDATE_NODE), new Generic<LinodeResponse<NodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeIdResponse> deleteNode(DeleteNodeRequest deleteNodeRequest)
	{
		return post(url(), deleteNodeRequest.setApiAction(DELETE_NODE), new Generic<LinodeResponse<NodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeIdResponse> cloneNode(CloneNodeRequest cloneNodeRequest)
	{
		return post(url(), cloneNodeRequest.setApiAction(CLONE_NODE), new Generic<LinodeResponse<NodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeJobIdResponse> bootNode(BootNodeRequest bootNodeRequest)
	{
		return post(url(), bootNodeRequest.setApiAction(BOOT_NODE), new Generic<LinodeResponse<NodeJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeJobIdResponse> rebootNode(RebootNodeRequest rebootNodeRequest)
	{
		return post(url(), rebootNodeRequest.setApiAction(REBOOT_NODE), new Generic<LinodeResponse<NodeJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeJobIdResponse> shutdownNode(ShutdownNodeRequest shutdownNodeRequest)
	{
		return post(url(), shutdownNodeRequest.setApiAction(SHUTDOWN_NODE), new Generic<LinodeResponse<NodeJobIdResponse>>(){});
	}
	
	public LinodeResponse<Void> resizeNode(ResizeNodeRequest resizeNodeRequest)
	{
		return post(url(), resizeNodeRequest.setApiAction(RESIZE_NODE), new Generic<LinodeResponse<Void>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeConfig>> getNodeConfig(GetNodeConfigRequest getNodeConfigRequest)
	{
		return post(url(), getNodeConfigRequest.setApiAction(GET_NODE_CONFIG), new Generic<LinodeResponse<LinkedList<NodeConfig>>>(){});
	}
	
	public LinodeResponse<NodeConfigIdResponse> createNodeConfig(CreateNodeConfigRequest createNodeConfigRequest)
	{
		return post(url(), createNodeConfigRequest.setApiAction(CREATE_NODE_CONFIG), new Generic<LinodeResponse<NodeConfigIdResponse>>(){});
	}
	
	public LinodeResponse<NodeConfigIdResponse> updateNodeConfig(UpdateNodeConfigRequest updateNodeConfigRequest)
	{
		return post(url(), updateNodeConfigRequest.setApiAction(UPDATE_NODE_CONFIG), new Generic<LinodeResponse<NodeConfigIdResponse>>(){});
	}
	
	public LinodeResponse<NodeConfigIdResponse> deleteNodeConfig(DeleteNodeConfigRequest deleteNodeConfigRequest)
	{
		return post(url(), deleteNodeConfigRequest.setApiAction(DELETE_NODE_CONFIG), new Generic<LinodeResponse<NodeConfigIdResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeDisk>> getNodeDisk(GetNodeDiskRequest getNodeDiskRequest)
	{
		return post(url(), getNodeDiskRequest.setApiAction(GET_NODE_DISK), new Generic<LinodeResponse<LinkedList<NodeDisk>>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> createNodeDisk(CreateNodeDiskRequest createNodeDiskRequest)
	{
		return post(url(), createNodeDiskRequest.setApiAction(CREATE_NODE_DISK), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> createNodeDiskFromDisk(CreateNodeDiskFromDistRequest createNodeDiskFromDistRequest)
	{
		return post(url(), createNodeDiskFromDistRequest.setApiAction(CREATE_NODE_DISK_FROM_DIST), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> createNodeDiskFromScript(CreateNodeDiskFromScriptRequest createNodeDiskFromScriptRequest)
	{
		return post(url(), createNodeDiskFromScriptRequest.setApiAction(CREATE_NODE_DISK_FROM_SCRIPT), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskIdResponse> updateNodeDisk(UpdateNodeDiskRequest updateNodeDiskRequest)
	{
		return post(url(), updateNodeDiskRequest.setApiAction(UPDATE_NODE_DISK), new Generic<LinodeResponse<NodeDiskIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> deleteNodeDisk(DeleteNodeDiskRequest deleteNodeDiskRequest)
	{
		return post(url(), deleteNodeDiskRequest.setApiAction(DELETE_NODE_DISK), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> duplicateNodeDisk(DuplicateNodeDiskRequest duplicateNodeDiskRequest)
	{
		return post(url(), duplicateNodeDiskRequest.setApiAction(DUPLICATE_NODE_DISK), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<NodeDiskJobIdResponse> resizeNodeDisk(ResizeNodeDiskRequest resizeNodeDiskRequest)
	{
		return post(url(), resizeNodeDiskRequest.setApiAction(RESIZE_NODE_DISK), new Generic<LinodeResponse<NodeDiskJobIdResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeIp>> getNodeIp(GetNodeIpRequest getNodeIpRequest)
	{
		return post(url(), getNodeIpRequest.setApiAction(GET_NODE_IP), new Generic<LinodeResponse<LinkedList<NodeIp>>>(){});
	}
	
	public LinodeResponse<NodeIpResponse> addPrivateNodeIp(AddPrivateNodeIpRequest addPrivateNodeIpRequest)
	{
		return post(url(), addPrivateNodeIpRequest.setApiAction(ADD_PRIVATE_NODE_IP), new Generic<LinodeResponse<NodeIpResponse>>(){});
	}
	
	public LinodeResponse<NodeIpResponse> addPublicNodeIp(AddPublicNodeIpRequest addPublicNodeIpRequest)
	{
		return post(url(), addPublicNodeIpRequest.setApiAction(ADD_PUBLIC_NODE_IP), new Generic<LinodeResponse<NodeIpResponse>>(){});
	}
	
	public LinodeResponse<NodeIpResponse> setRdnsNodeIp(SetRdnsNodeIpRequest setRdnsNodeIpRequest)
	{
		return post(url(), setRdnsNodeIpRequest.setApiAction(SET_RDNS_NODE_IP), new Generic<LinodeResponse<NodeIpResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeIp>> swapNodeIp(SwapNodeIpRequest swapNodeIpRequest)
	{
		return post(url(), swapNodeIpRequest.setApiAction(SWAP_NODE_IP), new Generic<LinodeResponse<LinkedList<NodeIp>>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeJob>> getNodeJob(GetNodeJobRequest getNodeJobRequest)
	{
		return post(url(), getNodeJobRequest.setApiAction(GET_NODE_JOB), new Generic<LinodeResponse<LinkedList<NodeJob>>>(){});
	}
	
}
