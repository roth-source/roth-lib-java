package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class GetNodeJobRequest extends NodeIdRequest
{
	@Property(name = "JobID")
	protected Integer jobId;
	
	@Property(name = "pendingOnly")
	protected Boolean pendingOnly;
	
	public GetNodeJobRequest()
	{
		
	}
	
	public Integer getJobId()
	{
		return jobId;
	}
	
	public Boolean getPendingOnly()
	{
		return pendingOnly;
	}
	
	public GetNodeJobRequest setJobId(Integer jobId)
	{
		this.jobId = jobId;
		return this;
	}
	
	public GetNodeJobRequest setPendingOnly(Boolean pendingOnly)
	{
		this.pendingOnly = pendingOnly;
		return this;
	}
	
}
