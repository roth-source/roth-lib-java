package roth.lib.java.code.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import roth.lib.java.lang.List;
import java.util.regex.Pattern;

import roth.lib.java.Characters;

public class JavaTokenizer implements Characters
{
	public static Charset UTF_8 = Charset.forName("UTF-8");
	public static Pattern EXTENDS_PATTERN = Pattern.compile("\\s(?:extends|super)\\s");
	public static Pattern DEFINITION_PATTERN = Pattern.compile("^[\\w\\s$,]+$");
	public static List<Character> NUMBER_CHARACTERS = new List<Character>(_0, _1, _2, _3, _4, _5, _6, _7, _8, _9);
	public static List<Character> GENERIC_SPECIAL_CHARACTERS = new List<Character>(LEFT_ANGLE_BRACKET, RIGHT_ANGLE_BRACKET, EXCLAMATION, QUOTE, HASH, DOLLAR, PERCENT, AMPERSAND, SINGLE_QUOTE, LEFT_PAREN, RIGHT_PAREN, ASTERISK, PLUS, COMMA, DASH, SLASH, COLON, SEMI_COLON, EQUAL, BACKSLASH, CARET, GRAVE, LEFT_BRACE, BAR, RIGHT_BRACE, TILDE);
	
	protected JavaToken unitToken = new JavaToken(JavaTag.UNIT);
	protected int length = 10000;
	protected BufferedReader reader;
	
	public JavaTokenizer(File file) throws FileNotFoundException
	{
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
		length = (int) Math.min(file.length(), Integer.MAX_VALUE);
		tokenize();
		close();
	}
	
	public JavaTokenizer(InputStream input)
	{
		this(new BufferedReader(new InputStreamReader(input, UTF_8)));
	}
	
	protected JavaTokenizer(Reader reader)
	{
		this.reader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
		tokenize();
		close();
	}
	
	public JavaToken getUnitToken()
	{
		return unitToken;
	}
	
	protected JavaToken token(JavaToken groupToken, JavaToken token, JavaTag tag, Object value)
	{
		if(token != null && !token.getTag().equals(tag))
		{
			token = JavaTag.check(token);
		}
		if(token == null)
		{
			token = new JavaToken(tag);
			groupToken.add(token);
		}
		return token.append(value);
	}
	
	protected void tokenize()
	{
		tokenize(unitToken);
	}
	
	protected void tokenize(JavaToken groupToken)
	{
		int intValue;
		char value;
		try
		{
			JavaToken token = null;
			tokenize: 
			{
				while((intValue = reader.read()) != -1)
				{
					value = (char) intValue;
					switch(value)
					{
						// string enclosure character
						case QUOTE:
						{
							token = token(groupToken, token, JavaTag.STRING, value);
							token.append(readEscaped(value));
							token = null;
							break;
						}
						// character enclosure character
						case SINGLE_QUOTE:
						{
							token = token(groupToken, token, JavaTag.CHARACTER, value);
							token.append(readEscaped(value));
							token = null;
							break;
						}
						// comment character of inline (//), block (/*), or doc (/**)
						case SLASH:
						{
							if(peek(SLASH))
							{
								token = token(groupToken, token, JavaTag.COMMENT, INLINE_COMMENT);
								token.append(read(NEW_LINE));
								token = null;
							}
							else if(peek(ASTERISK))
							{
								token = token(groupToken, token, JavaTag.COMMENT, OPEN_BLOCK_COMMENT);
								token.append(read(CLOSE_BLOCK_COMMENT));
								token = null;
							}
							break;
						}
						// less than operator character or generic opening character
						case LEFT_ANGLE_BRACKET:
						{
							if(isGenericBlock())
							{
								token = JavaTag.check(token);
								tokenize(groupToken.add(new JavaToken(JavaTag.ANGLE_BRACKETS)));
							}
							else
							{
								token = token(groupToken, token, JavaTag.OPERATOR, value);
							}
							break;
						}
						// greater than operator character or generic closing character
						case RIGHT_ANGLE_BRACKET:
						{
							if(JavaTag.ANGLE_BRACKETS.equals(groupToken.getTag()))
							{
								break tokenize;
							}
							else
							{
								token = token(groupToken, token, JavaTag.OPERATOR, value);
								break;
							}
						}
						// paren opening character
						case LEFT_PAREN:
						{
							token = JavaTag.check(token);
							tokenize(groupToken.add(new JavaToken(JavaTag.PARENS)));
							break;
						}
						// paren closing character
						case RIGHT_PAREN:
						{
							break tokenize;
						}
						// block opening character
						case LEFT_BRACE:
						{
							token = JavaTag.check(token);
							tokenize(groupToken.add(new JavaToken(JavaTag.BRACES)));
							break;
						}
						// block closing character
						case RIGHT_BRACE:
						{
							break tokenize;
						}
						// array opening character
						case LEFT_BRACKET:
						{
							token = JavaTag.check(token);
							tokenize(groupToken.add(new JavaToken(JavaTag.BRACKETS)));
							break;
						}
						// array closing character
						case RIGHT_BRACKET:
						{
							break tokenize;
						}
						// statement end character
						case SEMI_COLON:
						{
							token = token(groupToken, token, JavaTag.END, value);
							break;
						}
						// separator character
						case COMMA:
						{
							token = token(groupToken, token, JavaTag.SEPERATOR, value);
							break;
						}
						// selector character
						case DOT:
						{
							Character c2 = null;
							if((c2 = peek(NUMBER_CHARACTERS)) != null)
							{
								token = token(groupToken, token, JavaTag.NAME, value);
								token.append(c2);
							}
							else if(peek(DOT, DOT))
							{
								token = token(groupToken, token, JavaTag.VARARGS, VARARGS);
							}
							else
							{
								token = token(groupToken, token, JavaTag.SELECTOR, value);
							}
							break;
						}
						// conditional operator character or wildcard character
						case QUESTION:
						{
							if(JavaTag.ANGLE_BRACKETS.equals(groupToken.getTag()))
							{
								token = token(groupToken, token, JavaTag.WILDCARD, value);
							}
							else
							{
								token = token(groupToken, token, JavaTag.OPERATOR, value);
							}
							break;
						}
						// conditional operator character or method reference character (::)
						case COLON:
						{
							if(peek(COLON))
							{
								token = token(groupToken, token, JavaTag.REFERENCE, REFERENCE);
							}
							else
							{
								token = token(groupToken, token, JavaTag.OPERATOR, value);
							}
							break;
						}
						// minus operator character or lambda character (->)
						case DASH:
						{
							if(peek(RIGHT_ANGLE_BRACKET))
							{
								token = token(groupToken, token, JavaTag.LAMBDA, LAMBDA);
							}
							else
							{
								token = token(groupToken, token, JavaTag.OPERATOR, value);
							}
							break;
						}
						// annotation character
						case AT:
						{
							token = token(groupToken, token, JavaTag.ANNOTATION, value);
							break;
						}
						// space characters
						case SPACE:
						case TAB:
						case NEW_LINE:
						case CARRIAGE_RETURN:
						case FORM_FEED:
						{
							token = token(groupToken, token, JavaTag.SPACE, value);
							break;
						}
						// name characters
						case A:
						case B:
						case C:
						case D:
						case E:
						case F:
						case G:
						case H:
						case I:
						case J:
						case K:
						case L:
						case M:
						case N:
						case O:
						case P:
						case Q:
						case R:
						case S:
						case T:
						case U:
						case V:
						case W:
						case X:
						case Y:
						case Z:
						case a:
						case b:
						case c:
						case d:
						case e:
						case f:
						case g:
						case h:
						case i:
						case j:
						case k:
						case l:
						case m:
						case n:
						case o:
						case p:
						case q:
						case r:
						case s:
						case t:
						case u:
						case v:
						case w:
						case x:
						case y:
						case z:
						case _0:
						case _1:
						case _2:
						case _3:
						case _4:
						case _5:
						case _6:
						case _7:
						case _8:
						case _9:
						case UNDERSCORE:
						case DOLLAR:
						{
							token = token(groupToken, token, JavaTag.NAME, value);
							break;
						}
						// operator characters
						case EQUAL:
						case PLUS:
						case ASTERISK:
						case PERCENT:
						case EXCLAMATION:
						case AMPERSAND:
						case BAR:
						case CARET:
						case TILDE:
						{
							token = token(groupToken, token, JavaTag.OPERATOR, value);
							break;
						}
						default:
						{
							// ignore
							break;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected boolean peek(Character...values)
	{
		try
		{
			int b;
			char c;
			reader.mark(values.length);
			for(int i = 0; i < values.length; i++)
			{
				b = reader.read();
				c = (char) b;
				if(b == -1 || !values[0].equals(c))
				{
					reader.reset();
					return false;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			try
			{
				reader.reset();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	protected Character peek(Collection<Character> values)
	{
		Character character = null;
		try
		{
			reader.mark(1);
			int b = reader.read();
			char c = (char) b;
			if(b == -1 || !values.contains(c))
			{
				reader.reset();
			}
			else
			{
				character = c;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			try
			{
				reader.reset();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
		}
		return character;
	}
	
	protected boolean peek(String value)
	{
		try
		{
			reader.mark(value.length() - 1);
			int b;
			char c;
			for(int i = 1; i < value.length(); i++)
			{
				b = reader.read();
				c = (char) b;
				if(b == -1 || value.charAt(i) != c)
				{
					reader.reset();
					return false;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			try
			{
				reader.reset();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	protected Result read(String...untils)
	{
		Result result = null;
		List<String> untilPeekList = new List<String>(untils);
		List<Character> untilList = new List<Character>();
		for(String untilPeek : untilPeekList)
		{
			untilList.add(untilPeek.charAt(0));
		}
		StringBuilder builder = new StringBuilder();
		if(!untilList.isEmpty())
		{
			try
			{
				int b;
				char c;
				while((b = reader.read()) != -1)
				{
					c = (char) b;
					int i = untilList.indexOf(c);
					if(i > -1)
					{
						String untilPeek = untilPeekList.get(i);
						if(untilPeek.length() > 1)
						{
							if(!peek(untilPeek))
							{
								builder.append(c);
							}
							else
							{
								result = new Result(untilPeek, builder.toString());
								break;
							}
						}
						else
						{
							result = new Result(untilPeek, builder.toString());
							break;
						}
					}
					else
					{
						builder.append(c);
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected Result read(Character...untils)
	{
		return read(Arrays.asList(untils));
	}
	
	protected Result read(Collection<Character> untils)
	{
		Result readResult = null;
		List<Character> untilList = new List<Character>().load(untils);
		StringBuilder builder = new StringBuilder();
		if(!untilList.isEmpty())
		{
			try
			{
				int b;
				char c;
				while((b = reader.read()) != -1)
				{
					c = (char) b;
					if(untilList.contains(c))
					{
						readResult = new Result(String.valueOf(c), builder.toString());
						break;
					}
					else
					{
						builder.append(c);
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return readResult;
	}
	
	protected String readEscaped(Character c)
	{
		StringBuilder value = new StringBuilder();
		Result result = null;
		Character lastCharacter = null;
		do
		{
			result = read(c);
			value.append(result);
			lastCharacter = !result.value.isEmpty() ? result.value.charAt(result.value.length() - 1) : null;
		}
		while(lastCharacter != null && lastCharacter.equals('\\'));
		return value.toString();
	}
	
	protected boolean isGenericBlock()
	{
		try
		{
			StringBuilder builder = new StringBuilder();
			reader.mark(length);
			int level = 1;
			Result result = null;
			do
			{
				result = read(GENERIC_SPECIAL_CHARACTERS);
				if(result != null)
				{
					if(level == 1)
					{
						builder.append(result.value);
					}
					if(result.untilEquals(RIGHT_ANGLE_BRACKET))
					{
						level--;
					}
					else if(result.untilEquals(LEFT_ANGLE_BRACKET))
					{
						level++;
					}
				}
				else
				{
					break;
				}
			}
			while(level > 0);
			reader.reset();
			String value = builder.toString();
			if(value.trim().isEmpty() || EXTENDS_PATTERN.matcher(value).find() || DEFINITION_PATTERN.matcher(value).find())
			{
				return true;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void close()
	{
		if(reader != null)
		{
			try
			{
				reader.close();
			}
			catch(IOException e)
			{
				
			}
		}
	}
	
	protected static class Result
	{
		protected String until;
		protected String value;
		
		public Result(String until, String value)
		{
			this.until = until;
			this.value = value != null ? value : BLANK;
		}
		
		public boolean untilEquals(Character character)
		{
			return until != null && !until.isEmpty() && character != null && character.equals(until.charAt(0));
		}
		
		@Override
		public String toString()
		{
			return value + until;
		}
		
	}
	
}
