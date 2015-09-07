package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeDiskRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CreateNodeDiskFromDistRequest extends NodeDiskRequest
{
	@Property(name = "DistributionID")
	protected Integer distributionId;
	
	@Property(name = "size")
	protected Integer size;
	
	@Property(name = "rootPass")
	protected String rootPass;
	
	@Property(name = "rootSSHKey")
	protected String rootSshKey;
	
	public CreateNodeDiskFromDistRequest(Integer linodeId, Integer distributionId, String label, Integer size, String rootPass)
	{
		super();
		setLinodeId(linodeId);
		setDistributionId(distributionId);
		setLabel(label);
		setSize(size);
		setRootPass(rootPass);
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
	
	public CreateNodeDiskFromDistRequest setDistributionId(Integer distributionId)
	{
		this.distributionId = distributionId;
		return this;
	}
	
	public CreateNodeDiskFromDistRequest setSize(Integer size)
	{
		this.size = size;
		return this;
	}
	
	public CreateNodeDiskFromDistRequest setRootPass(String rootPass)
	{
		this.rootPass = rootPass;
		return this;
	}
	
	public CreateNodeDiskFromDistRequest setRootSshKey(String rootSshKey)
	{
		this.rootSshKey = rootSshKey;
		return this;
	}
	
}
