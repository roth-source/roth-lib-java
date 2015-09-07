package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeDiskRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CreateNodeDiskRequest extends NodeDiskRequest
{
	@Property(name = "FromDistributionID")
	protected Integer fromDistributionId;
	
	@Property(name = "rootPass")
	protected String rootPass;
	
	@Property(name = "rootSSHKey")
	protected String rootSshKey;
	
	@Property(name = "type")
	protected String type;
	
	@Property(name = "size")
	protected Integer size;
	
	@Property(name = "isReadOnly")
	protected Boolean readOnly;
	
	public CreateNodeDiskRequest(Integer linodeId, String label, String type, Integer size)
	{
		super();
		setLinodeId(linodeId);
		setLabel(label);
		setType(type);
		setSize(size);
	}
	
	public Integer getFromDistributionId()
	{
		return fromDistributionId;
	}
	
	public String getRootPass()
	{
		return rootPass;
	}
	
	public String getRootSshKey()
	{
		return rootSshKey;
	}
	
	public String getType()
	{
		return type;
	}
	
	public Integer getSize()
	{
		return size;
	}
	
	public Boolean getReadOnly()
	{
		return readOnly;
	}
	
	public CreateNodeDiskRequest setFromDistributionId(Integer fromDistributionId)
	{
		this.fromDistributionId = fromDistributionId;
		return this;
	}
	
	public CreateNodeDiskRequest setRootPass(String rootPass)
	{
		this.rootPass = rootPass;
		return this;
	}
	
	public CreateNodeDiskRequest setRootSshKey(String rootSshKey)
	{
		this.rootSshKey = rootSshKey;
		return this;
	}
	
	public CreateNodeDiskRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public CreateNodeDiskRequest setSize(Integer size)
	{
		this.size = size;
		return this;
	}
	
	public CreateNodeDiskRequest setReadOnly(Boolean readOnly)
	{
		this.readOnly = readOnly;
		return this;
	}
	
}
