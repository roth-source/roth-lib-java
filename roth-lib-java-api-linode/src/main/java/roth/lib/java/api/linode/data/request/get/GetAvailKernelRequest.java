package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
@SuppressWarnings("serial")
public class GetAvailKernelRequest extends LinodeRequest
{
	@Property(name = "KernelID")
	protected Integer kernelId;
	
	@Property(name = "isXen")
	protected Integer xen;
	
	public GetAvailKernelRequest()
	{
		
	}
	
	public Integer getKernelId()
	{
		return kernelId;
	}
	
	public Integer getXen()
	{
		return xen;
	}
	
	public GetAvailKernelRequest setKernelId(Integer kernelId)
	{
		this.kernelId = kernelId;
		return this;
	}
	
	public GetAvailKernelRequest setXen(boolean xen)
	{
		this.xen = xen ? 1 : 0;
		return this;
	}
	
}
