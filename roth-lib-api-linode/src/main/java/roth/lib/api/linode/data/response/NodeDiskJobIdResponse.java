package roth.lib.api.linode.data.response;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeDiskJobIdResponse extends NodeDiskIdResponse
{
	@Property(name = "JobID")
	protected Integer jobId;
	
	public NodeDiskJobIdResponse()
	{
		
	}
	
	public Integer getJobId()
	{
		return jobId;
	}
	
	public NodeDiskJobIdResponse setJobId(Integer jobId)
	{
		this.jobId = jobId;
		return this;
	}
	
}
