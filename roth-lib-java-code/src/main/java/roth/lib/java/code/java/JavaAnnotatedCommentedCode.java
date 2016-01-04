package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaAnnotatedCommentedCode extends JavaCommentedCode
{
	protected LinkedList<JavaAnnotation> annotations = new LinkedList<JavaAnnotation>();
	
	protected JavaAnnotatedCommentedCode()
	{
		
	}

	public LinkedList<JavaAnnotation> getAnnotations()
	{
		return annotations;
	}
	
	public void setAnnotations(LinkedList<JavaAnnotation> annotations)
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
