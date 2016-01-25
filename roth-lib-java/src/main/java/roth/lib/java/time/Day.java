package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Day extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	public Day()
	{
		this(new GregorianCalendar());
	}
	
	public Day(int year, int month, int day)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	public Day(Date date)
	{
		this(toCalendar(date));
	}
	
	public Day(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Day(Calendar calendar)
	{
		super(calendar);
		setHour(0);
		setMinute(0);
		setSecond(0);
		setMillisecond(0);
	}
	
	
	
	@Override
	public String toString()
	{
		return format(DEFAULT_PATTERN);
	}
	
	public String format(String pattern)
	{
		return new SimpleDateFormat(pattern != null ? pattern : DEFAULT_PATTERN).format(toDate());
	}
	
	public static Day fromString(String value)
	{
		return new Day(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Day parse(String value, String pattern)
	{
		return new Day(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(this == object)
		{
			return true;
		}
		if(object == null)
		{
			return false;
		}
		if(getClass() != object.getClass())
		{
			return false;
		}
		Year other = (Year) object;
		if(getYear() != other.getYear())
		{
			return false;
		}
		if(getMonth() != other.getMonth())
		{
			return false;
		}
		if(getDay() != other.getDay())
		{
			return false;
		}
		return true;
	}
	
}
