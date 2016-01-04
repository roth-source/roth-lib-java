package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaSuperWildcard extends JavaWildcard
{
	protected JavaType boundType;
	
	public JavaSuperWildcard()
	{
		
	}
	
	public JavaSuperWildcard(JavaType boundType)
	{
		this.boundType = boundType;
	}
	
	public JavaType getBoundType()
	{
		return boundType;
	}
	
	public void setBoundType(JavaType boundType)
	{
		this.boundType = boundType;
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(QUESTION);
		builder.append(SPACE);
		builder.append(JavaTag.SUPER);
		builder.append(SPACE);
		builder.append(boundType);
		return builder.toString();
	}
	
}
