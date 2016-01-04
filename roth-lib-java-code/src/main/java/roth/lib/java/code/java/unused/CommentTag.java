package roth.lib.java.code.java.unused;

public enum CommentTag
{
	DOC,
	BLOCK,
	INLINE;
	
	public boolean isBlock()
	{
		return BLOCK.equals(this) || DOC.equals(this);
	}
	
}
