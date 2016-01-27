/**
 * The MIT License (MIT)
 * 
 * Copyright (c) <year> <copyright holders>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package roth.lib.java.code.java.test;

import static java.io.File.separator;
import static javax.annotation.processing.Completions.of;
import static roth.lib.java.code.java.JavaTag.WILDCARD;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Arrays;
import roth.lib.java.lang.Map;

import javax.naming.NamingException;

import javafx.util.converter.CharacterStringConverter;
import roth.lib.java.code.java.JavaCompilationUnit;
import roth.lib.java.lang.List;

/**
 * Class comments
 * @author User
 *
 */
@SuppressWarnings({"serial", "restriction"})
public abstract class TestClass<T extends List<? extends String[]>> extends Object implements Serializable
{
	/**
	 * public member doc comment
	 */
	public String publicMember;
	
	/**
	 * protected member doc comment
	 */
	protected String protectedMember;
	
	/**
	 * default member doc comment
	 */
	String defaultMember;
	
	/**
	 * private member doc comment
	 */
	private String privateMember;
	
	/**
	 * private member doc comment
	 */
	protected Runnable annonymous = new Runnable()
	{
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * public static doc comment
	 */
	public static String PUBLIC_CONST = "public";
	
	/**
	 * protected static doc comment
	 */
	protected static String PROTECTED_CONST = "protected";
	
	/**
	 * default static doc comment
	 */
	static String DEFAULT_CONST = "default";
	
	/**
	 * private static doc comment
	 */
	@SuppressWarnings("unused")
	private static String PRIVATE_CONST = "private";
	
	static
	{
		PRIVATE_CONST = "new value";
	}
	
	{
		privateMember = "test";
	}
	
	/**
	 * constructor doc comment
	 */
	public TestClass()
	{
		
	}
	
	/**
	 * constructor doc comment
	 */
	public TestClass(String privateMember, String protectedMember)
	{
		this.privateMember = privateMember;
		this.protectedMember = protectedMember; 
	}
	
	/**
	 * public static doc comment
	 */
	public static List<String> getList()
	{
		return new List<String>();
	}
	
	/**
	 * public member doc comment
	 */
	public void testIf(Map<String, String> testMap) throws NamingException
	{
		if(privateMember == null) throw new IllegalArgumentException();
		if(privateMember.equals("test"))
		{
			System.out.println("test");
		}
		else if(privateMember.equals("test2"))
		{
			System.out.println("test2");
		}
		else
		{
			System.out.println("other");
		}
		
		if(privateMember.equals("test"))
			System.out.println("test");
		else if(privateMember.equals("test2"))
			System.out.println("test2");
		else
			System.out.println("other");
		
	}
	
	public void testFor()
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.println("my loop");
		}
		for(int i = 0; i < 10; i++)
			System.out.println("my loop");
		for(String test : Arrays.asList("test1", "test2"))
		{
			System.out.println(test);
		}
		for(String test : Arrays.asList("test1", "test2"))
			System.out.println(test);
	}
	
	public void testWhile()
	{
		int i = 0;
		while(i++ < 10)
		{
			System.out.println(i);
		}
		while(i++ < 20)
			System.out.println(i);
		do
		{
			System.out.println(i);
		}
		while(i++ < 30);
		do
			System.out.println(i);
		while(i++ < 40);
	}
	
	public void testTry()
	{
		try
		{
			System.out.println("try");
		}
		catch(NullPointerException | IllegalArgumentException e)
		{
			System.out.println("catch");
		}
		catch(RuntimeException e)
		{
			System.out.println("catch");
		}
		catch(Exception e)
		{
			System.out.println("catch");
		}
		finally
		{
			System.out.println("finally");
		}
		try(final FileWriter writer = new FileWriter(new File("")); final FileWriter writer2 = new FileWriter(new File(""));)
		{
			System.out.println("writer");
			System.out.println(separator);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JavaCompilationUnit unit = new JavaCompilationUnit();
		unit.getClass();
		of("test");
		WILDCARD.name();
		new CharacterStringConverter().toString();
		
	}
	
	public void testSwitch()
	{
		int i = 10;
		switch(i)
		{
			case 9:
			case 10:
				System.out.println(i);
				break;
			case 11:
				System.out.println(i);
				break;
			default:
				System.out.println(i);
				break;
		}
	}
	
	/*
	public static @interface Test
	{
		String name();
		
	}
	*/
}
