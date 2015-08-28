package roth.lib.template;

public class TemplateConfig
{
	protected String openUnescapedExpression;
	protected String openEscapedExpression;
	protected String openStatement;
	protected String closeUnescapedExpression;
	protected String closeEscapedExpression;
	protected String closeStatement;
	protected String dataVar;
	protected String escapeVar;
	protected String issetVar;
	protected String argVar;
	protected String tempVar;
	protected String sourceVar;
	
	public TemplateConfig()
	{
		
	}
	
	public String getOpenUnescapedExpression()
	{
		return openUnescapedExpression;
	}
	
	public String getOpenEscapedExpression()
	{
		return openEscapedExpression;
	}
	
	public String getOpenStatement()
	{
		return openStatement;
	}
	
	public String getCloseUnescapedExpression()
	{
		return closeUnescapedExpression;
	}
	
	public String getCloseEscapedExpression()
	{
		return closeEscapedExpression;
	}
	
	public String getCloseStatement()
	{
		return closeStatement;
	}
	
	public String getDataVar()
	{
		return dataVar;
	}
	
	public String getEscapeVar()
	{
		return escapeVar;
	}
	
	public String getIssetVar()
	{
		return issetVar;
	}
	
	public String getArgVar()
	{
		return argVar;
	}
	
	public String getTempVar()
	{
		return tempVar;
	}
	
	public String getSourceVar()
	{
		return sourceVar;
	}
	
	public TemplateConfig setOpenUnescapedExpression(String openUnescapedExpression)
	{
		this.openUnescapedExpression = openUnescapedExpression;
		return this;
	}
	
	public TemplateConfig setOpenEscapedExpression(String openEscapedExpression)
	{
		this.openEscapedExpression = openEscapedExpression;
		return this;
	}
	
	public TemplateConfig setOpenStatement(String openStatement)
	{
		this.openStatement = openStatement;
		return this;
	}
	
	public TemplateConfig setCloseUnescapedExpression(String closeUnescapedExpression)
	{
		this.closeUnescapedExpression = closeUnescapedExpression;
		return this;
	}
	
	public TemplateConfig setCloseEscapedExpression(String closeEscapedExpression)
	{
		this.closeEscapedExpression = closeEscapedExpression;
		return this;
	}
	
	public TemplateConfig setCloseStatement(String closeStatement)
	{
		this.closeStatement = closeStatement;
		return this;
	}
	
	public TemplateConfig setDataVar(String dataVar)
	{
		this.dataVar = dataVar;
		return this;
	}
	
	public TemplateConfig setEscapeVar(String escapeVar)
	{
		this.escapeVar = escapeVar;
		return this;
	}
	
	public TemplateConfig setIssetVar(String issetVar)
	{
		this.issetVar = issetVar;
		return this;
	}
	
	public TemplateConfig setArgVar(String argVar)
	{
		this.argVar = argVar;
		return this;
	}
	
	public TemplateConfig setTempVar(String tempVar)
	{
		this.tempVar = tempVar;
		return this;
	}
	
	public TemplateConfig setSourceVar(String sourceVar)
	{
		this.sourceVar = sourceVar;
		return this;
	}
	
}
