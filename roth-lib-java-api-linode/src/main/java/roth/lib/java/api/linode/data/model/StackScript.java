package roth.lib.java.api.linode.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class StackScript implements Serializable
{
	@Property(name = "STACKSCRIPTID")
	protected Integer stackScriptId;
	
	@Property(name = "SCRIPT")
	protected String script;
	
	@Property(name = "DESCRIPTION")
	protected String description;
	
	@Property(name = "DISTRIBUTIONIDLIST")
	protected String distributionIdList;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "DEPLOYMENTSTOTAL")
	protected Integer deploymentsTotal;
	
	@Property(name = "LATESTREV")
	protected Integer latestRev;
	
	@Property(name = "CREATE_DT")
	protected Calendar createDt;
	
	@Property(name = "DEPLOYMENTSACTIVE")
	protected Integer deploymentsActive;
	
	@Property(name = "REV_NOTE")
	protected String revNote;
	
	@Property(name = "REV_DT")
	protected Calendar revDt;
	
	@Property(name = "USERID")
	protected Integer userId;
	
	@Property(name = "ISPUBLIC")
	protected Boolean _public;
	
	public StackScript()
	{
		
	}
	
	public Integer getStackScriptId()
	{
		return stackScriptId;
	}
	
	public String getScript()
	{
		return script;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getDistributionIdList()
	{
		return distributionIdList;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Integer getDeploymentsTotal()
	{
		return deploymentsTotal;
	}
	
	public Integer getLatestRev()
	{
		return latestRev;
	}
	
	public Calendar getCreateDt()
	{
		return createDt;
	}
	
	public Integer getDeploymentsActive()
	{
		return deploymentsActive;
	}
	
	public String getRevNote()
	{
		return revNote;
	}
	
	public Calendar getRevDt()
	{
		return revDt;
	}
	
	public Integer getUserId()
	{
		return userId;
	}
	
	public Boolean getPublic()
	{
		return _public;
	}
	
	public StackScript setStackScriptId(Integer stackScriptId)
	{
		this.stackScriptId = stackScriptId;
		return this;
	}
	
	public StackScript setScript(String script)
	{
		this.script = script;
		return this;
	}
	
	public StackScript setDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public StackScript setDistributionIdList(String distributionIdList)
	{
		this.distributionIdList = distributionIdList;
		return this;
	}
	
	public StackScript setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public StackScript setDeploymentsTotal(Integer deploymentsTotal)
	{
		this.deploymentsTotal = deploymentsTotal;
		return this;
	}
	
	public StackScript setLatestRev(Integer latestRev)
	{
		this.latestRev = latestRev;
		return this;
	}
	
	public StackScript setCreateDt(Calendar createDt)
	{
		this.createDt = createDt;
		return this;
	}
	
	public StackScript setDeploymentsActive(Integer deploymentsActive)
	{
		this.deploymentsActive = deploymentsActive;
		return this;
	}
	
	public StackScript setRevNote(String revNote)
	{
		this.revNote = revNote;
		return this;
	}
	
	public StackScript setRevDt(Calendar revDt)
	{
		this.revDt = revDt;
		return this;
	}
	
	public StackScript setUserId(Integer userId)
	{
		this.userId = userId;
		return this;
	}
	
	public StackScript setPublic(Boolean _public)
	{
		this._public = _public;
		return this;
	}
	
}
