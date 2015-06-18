package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeDiskRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class CreateNodeDiskFromScriptRequest extends NodeDiskRequest
{
	@Property(name = "StackScriptID")
	protected Integer stackScriptId;
	
	@Property(name = "StackScriptUDFResponses")
	protected String stackScriptUdfResponses;
	
	@Property(name = "DistributionID")
	protected Integer distributionId;
	
	@Property(name = "size")
	protected Integer size;
	
	@Property(name = "rootPass")
	protected String rootPass;
	
	@Property(name = "rootSSHKey")
	protected String rootSshKey;
	
	public CreateNodeDiskFromScriptRequest(Integer linodeId, Integer stackScriptId, String stackScriptUdfResponses, Integer distributionId, String label, Integer size, String rootPass)
	{
		super();
		setLinodeId(linodeId);
		setStackScriptId(stackScriptId);
		setStackScriptUdfResponses(stackScriptUdfResponses);
		setDistributionId(distributionId);
		setLabel(label);
		setSize(size);
		setRootPass(rootPass);
	}
	
	public Integer getStackScriptId()
	{
		return stackScriptId;
	}
	
	public String getStackScriptUdfResponses()
	{
		return stackScriptUdfResponses;
	}
	
	public Integer getDistributionId()
	{
		return distributionId;
	}
	
	public Integer getSize()
	{
		return size;
	}
	
	public String getRootPass()
	{
		return rootPass;
	}
	
	public String getRootSshKey()
	{
		return rootSshKey;
	}
	
	public CreateNodeDiskFromScriptRequest setStackScriptId(Integer stackScriptId)
	{
		this.stackScriptId = stackScriptId;
		return this;
	}
	
	public CreateNodeDiskFromScriptRequest setStackScriptUdfResponses(String stackScriptUdfResponses)
	{
		this.stackScriptUdfResponses = stackScriptUdfResponses;
		return this;
	}
	
	public CreateNodeDiskFromScriptRequest setDistributionId(Integer distributionId)
	{
		this.distributionId = distributionId;
		return this;
	}
	
	public CreateNodeDiskFromScriptRequest setSize(Integer size)
	{
		this.size = size;
		return this;
	}
	
	public CreateNodeDiskFromScriptRequest setRootPass(String rootPass)
	{
		this.rootPass = rootPass;
		return this;
	}
	
	public CreateNodeDiskFromScriptRequest setRootSshKey(String rootSshKey)
	{
		this.rootSshKey = rootSshKey;
		return this;
	}
	
}
