package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Second extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public Second()
	{
		this(new GregorianCalendar());
	}
	
	public Second(int year, int month, int day, int hour, int minute, int second)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	
	public Second(Date date)
	{
		this(toCalendar(date));
	}
	
	public Second(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Second(Calendar calendar)
	{
		super(calendar);
		setMillisecond(0);
	}
	
	public Second addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Second subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Second addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Second subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Second addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Second subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Second addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Second subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Second addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Second subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Second addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Second subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Second addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Second subtractMillisecond(int millisecond)
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
	
	public static Second fromString(String value)
	{
		return new Second(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Second parse(String value, String pattern)
	{
		return new Second(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		if(getHour() != other.getHour())
		{
			return false;
		}
		if(getSecond() != other.getSecond())
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Second clone()
	{
		return new Second((Calendar) calendar.clone());
	}
	
}
