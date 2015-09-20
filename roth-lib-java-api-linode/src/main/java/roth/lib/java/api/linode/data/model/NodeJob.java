package roth.lib.java.api.linode.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeJob implements Serializable
{
	@Property(name = "LINODEID")
	protected Integer linodeId;
	
	@Property(name = "JOBID")
	protected Integer jobId;
	
	@Property(name = "ACTION")
	protected String action;
	
	@Property(name = "ENTERED_DT")
	protected Calendar enteredDt;
	
	@Property(name = "HOST_START_DT")
	protected Calendar hostStartDt;
	
	@Property(name = "HOST_FINISH_DT")
	protected Calendar hostFinishDt;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "DURATION")
	protected Integer duration;
	
	@Property(name = "HOST_SUCCESS")
	protected Boolean hostSuccess;
	
	@Property(name = "HOST_MESSAGE")
	protected String hostMessage;
	
	public NodeJob()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public Integer getJobId()
	{
		return jobId;
	}
	
	public String getAction()
	{
		return action;
	}
	
	public Calendar getEnteredDt()
	{
		return enteredDt;
	}
	
	public Calendar getHostStartDt()
	{
		return hostStartDt;
	}
	
	public Calendar getHostFinishDt()
	{
		return hostFinishDt;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Integer getDuration()
	{
		return duration;
	}
	
	public Boolean getHostSuccess()
	{
		return hostSuccess;
	}
	
	public String getHostMessage()
	{
		return hostMessage;
	}
	
	public NodeJob setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
	public NodeJob setJobId(Integer jobId)
	{
		this.jobId = jobId;
		return this;
	}
	
	public NodeJob setAction(String action)
	{
		this.action = action;
		return this;
	}
	
	public NodeJob setEnteredDt(Calendar enteredDt)
	{
		this.enteredDt = enteredDt;
		return this;
	}
	
	public NodeJob setHostStartDt(Calendar hostStartDt)
	{
		this.hostStartDt = hostStartDt;
		return this;
	}
	
	public NodeJob setHostFinishDt(Calendar hostFinishDt)
	{
		this.hostFinishDt = hostFinishDt;
		return this;
	}
	
	public NodeJob setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeJob setDuration(Integer duration)
	{
		this.duration = duration;
		return this;
	}
	
	public NodeJob setHostSuccess(Boolean hostSuccess)
	{
		this.hostSuccess = hostSuccess;
		return this;
	}
	
	public NodeJob setHostMessage(String hostMessage)
	{
		this.hostMessage = hostMessage;
		return this;
	}
	
}
