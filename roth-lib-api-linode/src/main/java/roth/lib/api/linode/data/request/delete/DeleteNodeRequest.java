package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Property;

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
