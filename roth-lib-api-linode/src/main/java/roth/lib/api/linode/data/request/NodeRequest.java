package roth.lib.api.linode.data.request;

import roth.lib.api.linode.data.type.PaymentTermType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class NodeRequest extends LinodeRequest
{
	@Property(name = "DatacenterID")
	protected Integer datacenterId;
	
	@Property(name = "PlanID")
	protected Integer planId;
	
	@Property(name = "PaymentTerm")
	protected Integer paymentTerm;
	
	public NodeRequest()
	{
		
	}
	
	public Integer getDatacenterId()
	{
		return datacenterId;
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public Integer getPaymentTerm()
	{
		return paymentTerm;
	}
	
	public NodeRequest setDatacenterId(Integer datacenterId)
	{
		this.datacenterId = datacenterId;
		return this;
	}
	
	public NodeRequest setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
	public NodeRequest setPaymentTerm(Integer paymentTerm)
	{
		this.paymentTerm = paymentTerm;
		return this;
	}
	
	public NodeRequest setPaymentTerm(PaymentTermType paymentTermType)
	{
		this.paymentTerm = paymentTermType.get();
		return this;
	}
	
}
