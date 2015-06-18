package roth.lib.api.linode.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class StackScriptRequest extends LinodeRequest
{
	@Property(name = "Label")
	protected String label;
	
	@Property(name = "Description")
	protected String description;
	
	@Property(name = "DistributionIDList")
	protected String distributionIdList;
	
	@Property(name = "isPublic")
	protected Boolean _public;
	
	@Property(name = "rev_note")
	protected String revNote;
	
	@Property(name = "script")
	protected String script;
	
	public StackScriptRequest()
	{
		
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getDistributionIdList()
	{
		return distributionIdList;
	}
	
	public Boolean getPublic()
	{
		return _public;
	}
	
	public String getRevNote()
	{
		return revNote;
	}
	
	public String getScript()
	{
		return script;
	}
	
	public StackScriptRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public StackScriptRequest setDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public StackScriptRequest setDistributionIdList(String distributionIdList)
	{
		this.distributionIdList = distributionIdList;
		return this;
	}
	
	public StackScriptRequest setPublic(Boolean _public)
	{
		this._public = _public;
		return this;
	}
	
	public StackScriptRequest setRevNote(String revNote)
	{
		this.revNote = revNote;
		return this;
	}
	
	public StackScriptRequest setScript(String script)
	{
		this.script = script;
		return this;
	}
	
}
