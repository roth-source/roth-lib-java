package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.IMPORT;
import static roth.lib.java.code.java.JavaTag.STATIC;

@SuppressWarnings("serial")
public class JavaImport extends JavaInlineCommentedCode
{
	protected boolean _static;
	protected String name;
	
	public JavaImport()
	{
		
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public void setStatic(boolean _static)
	{
		this._static = _static;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
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
		builder.append(IMPORT);
		builder.append(SPACE);
		if(_static)
		{
			builder.append(STATIC);
			builder.append(SPACE);
		}
		builder.append(name);
		builder.append(SEMI_COLON);
		return builder.toString();
	}
	
}
