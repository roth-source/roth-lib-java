package roth.lib.api.linode.client;

import java.util.LinkedList;

import roth.lib.Generic;
import roth.lib.api.linode.data.model.NodeBalancer;
import roth.lib.api.linode.data.model.NodeBalancerConfig;
import roth.lib.api.linode.data.model.NodeBalancerNode;
import roth.lib.api.linode.data.request.create.CreateNodeBalancerConfigRequest;
import roth.lib.api.linode.data.request.create.CreateNodeBalancerNodeRequest;
import roth.lib.api.linode.data.request.create.CreateNodeBalancerRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeBalancerConfigRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeBalancerNodeRequest;
import roth.lib.api.linode.data.request.delete.DeleteNodeBalancerRequest;
import roth.lib.api.linode.data.request.get.GetNodeBalancerConfigRequest;
import roth.lib.api.linode.data.request.get.GetNodeBalancerNodeRequest;
import roth.lib.api.linode.data.request.get.GetNodeBalancerRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeBalancerConfigRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeBalancerNodeRequest;
import roth.lib.api.linode.data.request.update.UpdateNodeBalancerRequest;
import roth.lib.api.linode.data.response.LinodeResponse;
import roth.lib.api.linode.data.response.NodeBalancerConfigIdResponse;
import roth.lib.api.linode.data.response.NodeBalancerIdResponse;
import roth.lib.api.linode.data.response.NodeBalancerNodeIdResponse;

public class NodeBalancerClient extends LinodeClient
{
	
	public NodeBalancerClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public NodeBalancerClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<LinkedList<NodeBalancer>> getNodeBalancers()
	{
		return getNodeBalancer(new GetNodeBalancerRequest());
	}
	
	public LinodeResponse<LinkedList<NodeBalancer>> getNodeBalancer(GetNodeBalancerRequest getNodeBalancerRequest)
	{
		return post(url(), getNodeBalancerRequest.setApiAction(GET_NODE_BALANCER), new Generic<LinodeResponse<LinkedList<NodeBalancer>>>(){});
	}
	
	public LinodeResponse<NodeBalancerIdResponse> createNodeBalancer(CreateNodeBalancerRequest createNodeBalancerRequest)
	{
		return post(url(), createNodeBalancerRequest.setApiAction(CREATE_NODE_BALANCER), new Generic<LinodeResponse<NodeBalancerIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerIdResponse> updateNodeBalancer(UpdateNodeBalancerRequest updateNodeBalancerRequest)
	{
		return post(url(), updateNodeBalancerRequest.setApiAction(UPDATE_NODE_BALANCER), new Generic<LinodeResponse<NodeBalancerIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerIdResponse> deleteNodeBalancer(DeleteNodeBalancerRequest deleteNodeBalancerRequest)
	{
		return post(url(), deleteNodeBalancerRequest.setApiAction(DELETE_NODE_BALANCER), new Generic<LinodeResponse<NodeBalancerIdResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeBalancerConfig>> getNodeBalancerConfigs(GetNodeBalancerConfigRequest getNodeBalancerConfigRequest)
	{
		return post(url(), getNodeBalancerConfigRequest.setApiAction(GET_NODE_BALANCER_CONFIG), new Generic<LinodeResponse<LinkedList<NodeBalancerConfig>>>(){});
	}
	
	public LinodeResponse<NodeBalancerConfigIdResponse> createNodeBalancerConfig(CreateNodeBalancerConfigRequest createNodeBalancerConfigRequest)
	{
		return post(url(), createNodeBalancerConfigRequest.setApiAction(CREATE_NODE_BALANCER_CONFIG), new Generic<LinodeResponse<NodeBalancerConfigIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerConfigIdResponse> updateNodeBalancerConfig(UpdateNodeBalancerConfigRequest updateNodeBalancerConfigRequest)
	{
		return post(url(), updateNodeBalancerConfigRequest.setApiAction(UPDATE_NODE_BALANCER_CONFIG), new Generic<LinodeResponse<NodeBalancerConfigIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerConfigIdResponse> deleteNodeBalancerConfig(DeleteNodeBalancerConfigRequest deleteNodeBalancerConfigRequest)
	{
		return post(url(), deleteNodeBalancerConfigRequest.setApiAction(DELETE_NODE_BALANCER_CONFIG), new Generic<LinodeResponse<NodeBalancerConfigIdResponse>>(){});
	}
	
	public LinodeResponse<LinkedList<NodeBalancerNode>> getNodeBalancerNodes(GetNodeBalancerNodeRequest getNodeBalancerNodeRequest)
	{
		return post(url(), getNodeBalancerNodeRequest.setApiAction(GET_NODE_BALANCER_NODE), new Generic<LinodeResponse<LinkedList<NodeBalancerNode>>>(){});
	}
	
	public LinodeResponse<NodeBalancerNodeIdResponse> createNodeBalancerNode(CreateNodeBalancerNodeRequest createNodeBalancerNodeRequest)
	{
		return post(url(), createNodeBalancerNodeRequest.setApiAction(CREATE_NODE_BALANCER_NODE), new Generic<LinodeResponse<NodeBalancerNodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerNodeIdResponse> updateNodeBalancerNode(UpdateNodeBalancerNodeRequest updateNodeBalancerNodeRequest)
	{
		return post(url(), updateNodeBalancerNodeRequest.setApiAction(UPDATE_NODE_BALANCER_NODE), new Generic<LinodeResponse<NodeBalancerNodeIdResponse>>(){});
	}
	
	public LinodeResponse<NodeBalancerNodeIdResponse> deleteNodeBalancerNode(DeleteNodeBalancerNodeRequest deleteNodeBalancerNodeRequest)
	{
		return post(url(), deleteNodeBalancerNodeRequest.setApiAction(DELETE_NODE_BALANCER_NODE), new Generic<LinodeResponse<NodeBalancerNodeIdResponse>>(){});
	}
	
}
