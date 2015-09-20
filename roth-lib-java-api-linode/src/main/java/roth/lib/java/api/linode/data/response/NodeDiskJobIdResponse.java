package roth.lib.java.api.linode.data.response;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
