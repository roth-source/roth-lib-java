package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaParameter extends JavaCode
{
	protected LinkedList<JavaAnnotation> annotations = new LinkedList<JavaAnnotation>();
	protected boolean _final;
	protected JavaType type;
	protected boolean varargs;
	protected String name;
	
	public JavaParameter()
	{
		
	}
	
	public LinkedList<JavaAnnotation> getAnnotations()
	{
		return annotations;
	}
	
	public boolean isFinal()
	{
		return _final;
	}
	
	public JavaType getType()
	{
		return type;
	}
	
	public boolean isVarargs()
	{
		return varargs;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAnnotations(LinkedList<JavaAnnotation> annotations)
	{
		this.annotations = annotations;
	}
	
	public void setFinal(boolean _final)
	{
		this._final = _final;
	}
	
	public void setType(JavaType type)
	{
		this.type = type;
	}
	
	public void setVarargs(boolean varargs)
	{
		this.varargs = varargs;
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
		for(JavaAnnotation annotation : annotations)
		{
			builder.append(annotation);
			builder.append(SPACE);
		}
		if(_final)
		{
			builder.append(JavaTag.FINAL);
			builder.append(SPACE);
		}
		builder.append(type);
		if(varargs)
		{
			builder.append(VARARGS);
		}
		builder.append(SPACE);
		builder.append(name);
		return builder.toString();
	}
	
}
