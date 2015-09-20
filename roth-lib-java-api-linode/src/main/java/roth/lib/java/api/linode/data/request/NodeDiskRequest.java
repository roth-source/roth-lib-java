package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
