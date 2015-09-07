package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeDiskRequest extends NodeIdRequest
{
	@Property(name = "Label")
	protected String label;
	
	public NodeDiskRequest()
	{
		
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public NodeDiskRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
}
