package roth.lib.java.code.java;

import roth.lib.java.lang.List;

import roth.lib.java.Characters;

public class JavaToken implements Characters
{
	protected JavaTag tag;
	protected StringBuilder builder = new StringBuilder();
	protected List<JavaToken> tokens = new List<JavaToken>();
	
	public JavaToken(JavaTag tag)
	{
		this.tag = tag;
	}
	
	public JavaTag getTag()
	{
		return tag;
	}
	
	public void setTag(JavaTag tag)
	{
		this.tag = tag;
	}

	public JavaToken append(Object value)
	{
		builder.append(value);
		return this;
	}
	
	public StringBuilder getBuilder()
	{
		return builder;
	}
	
	public String getValue()
	{
		return builder.toString();
	}
	
	public JavaToken add(JavaToken token)
	{
		tokens.add(token);
		return token;
	}
	
	public List<JavaToken> getTokens()
	{
		return tokens;
	}
	
	public boolean isGroup()
	{
		return tag.isGroup();
	}
	
	@SuppressWarnings("incomplete-switch")
	public Character getOpen()
	{
		Character open = null;
		switch(tag)
		{
			case BRACES:
			{
				open = LEFT_BRACE;
				break;
			}
			case PARENS:
			{
				open = LEFT_PAREN;
				break;
			}
			case BRACKETS:
			{
				open = LEFT_BRACKET;
				break;
			}
			case ANGLE_BRACKETS:
			{
				open = LEFT_ANGLE_BRACKET;
				break;
			}
		}
		return open;
	}
	
	@SuppressWarnings("incomplete-switch")
	public Character getClose()
	{
		Character close = null;
		switch(tag)
		{
			case BRACES:
			{
				close = RIGHT_BRACE;
				break;
			}
			case PARENS:
			{
				close = RIGHT_PAREN;
				break;
			}
			case BRACKETS:
			{
				close = RIGHT_BRACKET;
				break;
			}
			case ANGLE_BRACKETS:
			{
				close = RIGHT_ANGLE_BRACKET;
				break;
			}
		}
		return close;
	}
	
	public boolean hasNewLine()
	{
		return getValue().contains("\n");
	}
	
	@Override
	public String toString()
	{
		if(isGroup())
		{
			StringBuilder builder = new StringBuilder();
			Character open = getOpen();
			if(open != null)
			{
				builder.append(open);
			}
			for(JavaToken token : getTokens())
			{
				builder.append(token);
			}
			Character close = getClose();
			if(close != null)
			{
				builder.append(close);
			}
			return builder.toString();
		}
		else
		{
			return getValue();
		}
	}
	
}
