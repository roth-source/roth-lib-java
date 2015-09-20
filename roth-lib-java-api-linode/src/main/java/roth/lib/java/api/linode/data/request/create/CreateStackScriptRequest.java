package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.StackScriptRequest;

@SuppressWarnings("serial")
public class CreateStackScriptRequest extends StackScriptRequest
{
	
	public CreateStackScriptRequest(String label, String distributionIdList, String script)
	{
		super();
		setLabel(label);
		setDistributionIdList(distributionIdList);
		setScript(script);
	}
	
}
