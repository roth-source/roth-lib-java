package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class JavaCommentedCode extends JavaInlineCommentedCode
{
	protected List<JavaComment> comments = new List<JavaComment>();
	
	protected JavaCommentedCode()
	{
		
	}
	
	public List<JavaComment> getComments()
	{
		return comments;
	}
	
	public void setComments(List<JavaComment> comments)
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
