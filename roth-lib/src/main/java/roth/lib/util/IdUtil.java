package roth.lib.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;

public class IdUtil
{
	protected static final int DEFAULT_LENGTH 		= 14;
	protected static final int PREFIX_MIN_LENGTH 	= 1;
	protected static final int PREFIX_MAX_LENGTH 	= 3;
	protected static final int TIME_LENGTH 			= 7;
	protected static final int RANDOM_MIN_LENGTH 	= 1;
	protected static final int RANDOM_MAX_LENGTH 	= 10;
	protected static final Random RANDOM = new Random();
	protected static final LinkedHashMap<Integer, BigDecimal> MAX_VALUES = new LinkedHashMap<Integer, BigDecimal>();
	
	static
	{
		for(int length = RANDOM_MIN_LENGTH; length <= RANDOM_MAX_LENGTH; length++)
		{
			MAX_VALUES.put(length, BigDecimal.valueOf(length).pow(BaseUtil.BASE_62));
		}
	}
	
	protected IdUtil()
	{
		
	}
	
	protected static String now()
	{
		return time(System.currentTimeMillis());
	}
	
	protected static String date(Date date)
	{
		return time(date.getTime());
	}
	
	protected static String calendar(Calendar calendar)
	{
		return time(calendar.getTimeInMillis());
	}
	
	protected static String time(long time)
	{
		return StringUtil.padLeftLimit(BaseUtil.encodeBase62(time), TIME_LENGTH, BaseUtil.BASE_PAD);
	}
	
	public static String random(int length)
	{
		return StringUtil.padLeftLimit(BaseUtil.encodeBase62(randomNumber(length)), length, BaseUtil.BASE_PAD);
	}
	
	protected static BigInteger randomNumber(int length)
	{
		return MAX_VALUES.get(length).multiply(BigDecimal.valueOf(RANDOM.nextDouble())).toBigInteger();
		//return MAX_VALUES.get(length).multiply(BigDecimal.valueOf(new Random().nextDouble())).toBigInteger();
	}
	
	public static String uuid(String prefix)
	{
		return uuid(prefix, now());
	}
	
	public static String uuid(String prefix, int length)
	{
		return uuid(prefix, now(), length);
	}
	
	public static String uuid(String prefix, long time)
	{
		return uuid(prefix, time(time));
	}
	
	public static String uuid(String prefix, Date date)
	{
		return uuid(prefix, date(date));
	}
	
	public static String uuid(String prefix, Calendar calendar)
	{
		return uuid(prefix, calendar(calendar));
	}
	
	public static String uuid(String prefix, String time)
	{
		return uuid(prefix, time, DEFAULT_LENGTH);
	}
	
	public static String uuid(String prefix, long time, int length)
	{
		return uuid(prefix, time(time), length);
	}
	
	public static String uuid(String prefix, Date date, int length)
	{
		return uuid(prefix, date(date), length);
	}
	
	public static String uuid(String prefix, Calendar calendar, int length)
	{
		return uuid(prefix, calendar(calendar), length);
	}
	
	public static String uuid(String prefix, String time, int length)
	{
		if(prefix == null || prefix.length() < PREFIX_MIN_LENGTH || prefix.length() > PREFIX_MAX_LENGTH)
		{
			throw new IllegalArgumentException(String.format("prefix must be between %d and %d characters in length", PREFIX_MIN_LENGTH, PREFIX_MAX_LENGTH));
		}
		if(time.length() != TIME_LENGTH)
		{
			throw new IllegalArgumentException(String.format("time must be %d characters in length", TIME_LENGTH));
		}
		int staticLength = prefix.length() + TIME_LENGTH;
		int randomLength = length - staticLength;
		if(randomLength < RANDOM_MIN_LENGTH || randomLength > RANDOM_MAX_LENGTH)
		{
			throw new IllegalArgumentException(String.format("length must be between %d and %d" , (staticLength + RANDOM_MIN_LENGTH), (staticLength + RANDOM_MAX_LENGTH)));
		}
		StringBuilder builder = new StringBuilder();
		builder.append(prefix);
		builder.append(time);
		builder.append(random(randomLength));
		return builder.toString();
	}
	
	public static long uuidTime(String uuid, int prefixLength)
	{
		if(prefixLength < PREFIX_MIN_LENGTH || prefixLength > PREFIX_MAX_LENGTH)
		{
			throw new IllegalArgumentException("prefix must be between " + PREFIX_MIN_LENGTH + " and " + PREFIX_MAX_LENGTH + " characters in length");
		}
		return BaseUtil.decodeBase62(uuid.substring(prefixLength, prefixLength + TIME_LENGTH)).longValue();
	}
	
	public static Date uuidDate(String uuid, int prefixLength)
	{
		return new Date(uuidTime(uuid, prefixLength));
	}
	
	public static Calendar uuidCalendar(String uuid, int prefixLength)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(uuidTime(uuid, prefixLength));
		return calendar;
	}
	
	public static void main(String[] args)
	{
		System.out.println(System.nanoTime());
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		int count = 0;
		for(int i = 0; i < 1000000; i++)
		{
			String uuid = uuid("0", now(), 14);
			if(!set.add(uuid))
			{
				System.err.println(++count + ":" + uuid);
			}
		}
		System.out.println(System.nanoTime());
	}
	
}
