package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeIdRequest;

@Entity
@SuppressWarnings("serial")
public class DeleteNodeRequest extends NodeIdRequest
{
	@Property(name = "skipChecks")
	protected Boolean skipChecks;
	
	public DeleteNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public Boolean getSkipChecks()
	{
		return skipChecks;
	}
	
	public DeleteNodeRequest setSkipChecks(Boolean skipChecks)
	{
		this.skipChecks = skipChecks;
		return this;
	}
	
}
