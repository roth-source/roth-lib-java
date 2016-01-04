package roth.lib.java.code.java;

@SuppressWarnings("serial")
public class JavaInitializer extends JavaCode implements JavaMember
{
	protected boolean _static;
	protected JavaBlock block;
	
	public JavaInitializer()
	{
		
	}
	
	@Override
	public boolean isStatic()
	{
		return _static;
	}
	
	public JavaBlock getBlock()
	{
		return block;
	}
	
	public void setStatic(boolean _static)
	{
		this._static = _static;
	}
	
	public void setBlock(JavaBlock block)
	{
		this.block = block;
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		if(_static)
		{
			builder.append(indent(tabs));
			builder.append(JavaTag.STATIC);
			builder.append(NEW_LINE);
		}
		builder.append(block.toSource(tabs));
		return builder.toString();
	}
	
}
