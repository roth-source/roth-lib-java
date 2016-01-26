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
	
	public Day addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Day subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Day addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Day subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Day addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Day subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Day addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Day subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Day addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Day subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Day addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Day subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Day addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Day subtractMillisecond(int millisecond)
	{
		super.subtractMillisecond(millisecond);
		return this;
	}
	
	@Override
	public String toString()
	{
		return format(DEFAULT_PATTERN);
	}
	
	@Override
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
	
	@Override
	public Day clone()
	{
		return new Day((Calendar) calendar.clone());
	}
	
}
