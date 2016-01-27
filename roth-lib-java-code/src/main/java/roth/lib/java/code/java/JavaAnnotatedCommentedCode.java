package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaAnnotatedCommentedCode extends JavaCommentedCode
{
	protected List<JavaAnnotation> annotations = new List<JavaAnnotation>();
	
	protected JavaAnnotatedCommentedCode()
	{
		
	}

	public List<JavaAnnotation> getAnnotations()
	{
		return annotations;
	}
	
	public void setAnnotations(List<JavaAnnotation> annotations)
	{
		this.annotations.addAll(annotations);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		String indent = indent(tabs);
		builder.append(super.toSource(tabs));
		for(JavaAnnotation annotation : annotations)
		{
			builder.append(indent);
			builder.append(annotation.toSource(tabs));
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
}
