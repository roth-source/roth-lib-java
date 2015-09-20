package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeIdRequest;

@Entity
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
