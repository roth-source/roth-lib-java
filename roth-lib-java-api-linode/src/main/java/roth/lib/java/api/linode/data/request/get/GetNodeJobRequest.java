package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeIdRequest;

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
