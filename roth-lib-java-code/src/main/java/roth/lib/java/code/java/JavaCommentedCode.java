package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class JavaCommentedCode extends JavaInlineCommentedCode
{
	protected LinkedList<JavaComment> comments = new LinkedList<JavaComment>();
	
	protected JavaCommentedCode()
	{
		
	}
	
	public LinkedList<JavaComment> getComments()
	{
		return comments;
	}
	
	public void setComments(LinkedList<JavaComment> comments)
	{
		this.comments.addAll(comments);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		for(JavaComment comment : comments)
		{
			builder.append(comment.toSource(tabs));
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
}
