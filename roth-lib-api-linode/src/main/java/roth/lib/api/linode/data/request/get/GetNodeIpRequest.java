package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class GetNodeIpRequest extends NodeIdRequest
{
	@Property(name = "IPAddressID")
	protected Integer ipAddressId;
	
	public GetNodeIpRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public Integer getIpAddressId()
	{
		return ipAddressId;
	}
	
	public GetNodeIpRequest setIpAddressId(Integer ipAddressId)
	{
		this.ipAddressId = ipAddressId;
		return this;
	}
	
}
