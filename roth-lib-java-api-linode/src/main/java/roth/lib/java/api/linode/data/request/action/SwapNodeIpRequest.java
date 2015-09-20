package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
@SuppressWarnings("serial")
public class SwapNodeIpRequest extends LinodeRequest
{
	@Property(name = "IPAddressID")
	protected Integer ipAddressId;
	
	@Property(name = "withIPAddressID")
	protected Integer withIpAddressId;
	
	@Property(name = "toLinodeID")
	protected Integer toLinodeId;
	
	public SwapNodeIpRequest(Integer ipAddressId)
	{
		super();
		setIpAddressId(ipAddressId);
	}
	
	public Integer getIpAddressId()
	{
		return ipAddressId;
	}
	
	public Integer getWithIpAddressId()
	{
		return withIpAddressId;
	}
	
	public Integer getToLinodeId()
	{
		return toLinodeId;
	}
	
	public SwapNodeIpRequest setIpAddressId(Integer ipAddressId)
	{
		this.ipAddressId = ipAddressId;
		return this;
	}
	
	public SwapNodeIpRequest setWithIpAddressId(Integer withIpAddressId)
	{
		this.withIpAddressId = withIpAddressId;
		return this;
	}
	
	public SwapNodeIpRequest setToLinodeId(Integer toLinodeId)
	{
		this.toLinodeId = toLinodeId;
		return this;
	}
	
}
