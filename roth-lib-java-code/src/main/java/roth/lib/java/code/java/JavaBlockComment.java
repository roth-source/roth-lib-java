package roth.lib.java.code.java;


@SuppressWarnings("serial")
public class JavaBlockComment extends JavaComment
{
	
	public JavaBlockComment()
	{
		
	}
	
	@Override
	public String toSource(int tabs)
	{
		String indent = indent(tabs);
		StringBuilder builder = new StringBuilder();
		if(!lines.isEmpty())
		{
			builder.append(indent);
			String seperator = BLANK;
			for(String line : lines)
			{
				builder.append(seperator);
				builder.append(line.trim());
				seperator = NEW_LINE + indent + SPACE;
			}
			builder.append(indent);
		}
		return builder.toString();
	}
	
}
