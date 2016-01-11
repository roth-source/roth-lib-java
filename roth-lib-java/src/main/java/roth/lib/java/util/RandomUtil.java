package roth.lib.java.util;

import java.util.Random;

public class RandomUtil
{
	protected static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7','8', '9' };
	
	protected RandomUtil()
	{
		
	}
	
	public static String digits(int length)
	{
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for(int i = 0; i < length; i++)
		{
			if(i == 0)
			{
				builder.append(DIGITS[random.nextInt(9) + 1]);
			}
			else
			{
				builder.append(DIGITS[random.nextInt(10)]);
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(digits(400));
	}
	
}
