package roth.lib.java.code.java;


@SuppressWarnings("serial")
public class JavaWildcard extends JavaGeneric
{
	
	public JavaWildcard()
	{
		
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(QUESTION);
		return builder.toString();
	}
	
}
