package roth.lib.java.email.util;

import java.io.File;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.lang.List;
import roth.lib.java.util.FileUtil;

public class EmailReplyUtil
{
	protected static final Pattern NEWLINE_PATTERN = Pattern.compile("\\r?\\n");
	protected static final Pattern SIGNATURE_PATTERN = Pattern.compile("((^Sent from my (\\s*\\w+){1,3}$)|(^-\\w|^\\s?__|^\\s?--|^\u2013|^\u2014))", Pattern.DOTALL);
	protected static final List<Pattern> QUOTE_HEADER_PATTERNS = new List<>();
	
	static
	{
		QUOTE_HEADER_PATTERNS.add(Pattern.compile("^(On\\s(.{1,500})wrote:)"));
		QUOTE_HEADER_PATTERNS.add(Pattern.compile("^From:[^\\n]+\\n?([^\\n]+\\n?){0,2}To:[^\\n]+\\n?([^\\n]+\\n?){0,2}Subject:[^\\n]+"));
		QUOTE_HEADER_PATTERNS.add(Pattern.compile("^To:[^\\n]+\\n?([^\\n]+\\n?){0,2}From:[^\\n]+\\n?([^\\n]+\\n?){0,2}Subject:[^\\n]+"));
	}
	
	protected EmailReplyUtil()
	{
		
	}
	
	public static String format(List<String> lines)
	{
		StringBuilder builder = new StringBuilder();
		String seperator = "";
		for(String line : lines)
		{
			builder.append(seperator);
			builder.append(line);
			seperator = "\n";
		}
		return builder.toString();
	}
	
	public static List<String> parse(String value)
	{
		List<String> replyLines = new List<>();
		int index = 0;
		Matcher matcher = NEWLINE_PATTERN.matcher(value);
		while(matcher.find())
		{
			String line = value.substring(index, matcher.start()).trim();
			if(!(replyLines.isEmpty() && line.isEmpty()) && !isSignature(line))
			{
				if(!isQuoteHeader(value.substring(index)))
				{
					replyLines.add(line);
				}
				else
				{
					break;
				}
			}
			index = matcher.end();
		}
		ListIterator<String> replyLinesIterator = replyLines.listIterator(replyLines.size());
		while(replyLinesIterator.hasPrevious())
		{
			if(replyLinesIterator.previous().isEmpty())
			{
				replyLinesIterator.remove();
			}
			else
			{
				break;
			}
		}
		return replyLines;
	}
	
	public static boolean isSignature(String line)
	{
		return SIGNATURE_PATTERN.matcher(line).find();
	}
	
	public static boolean isQuoteHeader(String line)
	{
		boolean quoteHeader = false;
		for(Pattern quoteHeaderPattern : QUOTE_HEADER_PATTERNS)
		{
			if(quoteHeaderPattern.matcher(line).find())
			{
				quoteHeader = true;
				break;
			}
		}
		return quoteHeader;
	}
	
	public static void main(String[] args)
	{
		System.out.println(format(parse(FileUtil.toString(new File("dev/test-hotmail.txt")))));
		//System.out.println(format(parse(FileUtil.toString(new File("dev/test-gmail.txt")))));
		//System.out.println(parse(FileUtil.toString(new File("dev/test-yahoo.txt"))));
	}
	
}
