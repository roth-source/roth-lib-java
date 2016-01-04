package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaInlineComment extends JavaComment
{
	protected String space;
	
	public JavaInlineComment()
	{
		
	}
	
	@Override
	public String getSpace()
	{
		return space;
	}
	
	@Override
	public void setSpace(String space)
	{
		this.space = space;
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		if(!lines.isEmpty())
		{
			if(space != null)
			{
				builder.append(space);
			}
			else
			{
				builder.append(indent(tabs));
			}
			builder.append(lines.getFirst().trim());
		}
		return builder.toString();
	}
	
}
