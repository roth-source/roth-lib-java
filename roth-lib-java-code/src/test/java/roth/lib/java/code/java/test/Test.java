package roth.lib.java.code.java.test;

import java.io.File;

import roth.lib.java.code.java.JavaCompilationUnit;
import roth.lib.java.code.java.JavaParser;
import roth.lib.java.code.java.JavaToken;
import roth.lib.java.code.java.JavaTokenizer;

public class Test
{
	
	public static void main(String[] args) throws Exception
	{
		File file = new File("src/test/java/roth/lib/java/code/java/test/TestClass.java");
		//tokenize(file);
		parse(file);
	}
	
	protected static void tokenize(File file) throws Exception
	{
		JavaTokenizer tokenizer = new JavaTokenizer(file);
		printTag(tokenizer.getUnitToken(), 0);
		//System.out.println(tokenizer.getUnitToken());
	}
	
	public static void printTag(JavaToken token, int space)
	{
		for(int i = 0; i < space; i++)
		{
			System.out.print("\t");
		}
		System.out.println(token.getTag());
		if(token.isGroup())
		{
			space++;
			for(JavaToken child : token.getTokens())
			{
				printTag(child, space);
			}
		}
	}
	
	protected static void parse(File file) throws Exception
	{
		JavaParser parser = new JavaParser(file);
		JavaCompilationUnit unit = parser.getCompilationUnit();
		System.out.println(unit);
	}
	
	protected static void test()
	{
		label:
		{
			System.out.println("this is my label");
			for(int i = 0; i < 10; i++)
			{
				System.out.println(i);
				if(i == 9)
				{
					break label;
				}
			}
			System.out.println("end of label");
		}
	}
	
}
