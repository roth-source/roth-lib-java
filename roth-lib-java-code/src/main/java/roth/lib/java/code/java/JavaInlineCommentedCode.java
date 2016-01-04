package roth.lib.java.code.java;

@SuppressWarnings("serial")
public abstract class JavaInlineCommentedCode extends JavaCode
{
	protected JavaInlineComment inlineComment;
	
	protected JavaInlineCommentedCode()
	{
		
	}
	
	public JavaInlineComment getInlineComment()
	{
		return inlineComment;
	}
	
	public void setInlineComment(JavaInlineComment inlineComment)
	{
		this.inlineComment = inlineComment;
	}
	
}
