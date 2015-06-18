package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.LinodeRequest;
import roth.lib.api.linode.data.type.ModeType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class GetInvoiceEstimateRequest extends LinodeRequest
{
	@Property(name = "mode")
	protected String mode;
	
	@Property(name = "PaymentTerm")
	protected Integer paymentTerm;
	
	@Property(name = "PlanID")
	protected Integer planId;
	
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
	public GetInvoiceEstimateRequest(ModeType modeType)
	{
		super();
		setMode(modeType);
	}
	
	public String getMode()
	{
		return mode;
	}
	
	public Integer getPaymentTerm()
	{
		return paymentTerm;
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public GetInvoiceEstimateRequest setPaymentTerm(Integer paymentTerm)
	{
		this.paymentTerm = paymentTerm;
		return this;
	}
	
	public GetInvoiceEstimateRequest setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
	public GetInvoiceEstimateRequest setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
	public GetInvoiceEstimateRequest setMode(ModeType modeType)
	{
		this.mode = modeType.get();
		return this;
	}
	
}
