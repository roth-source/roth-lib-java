package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Minute extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";
	
	public Minute()
	{
		this(new GregorianCalendar());
	}
	
	public Minute(int year, int month, int day, int hour, int minute)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
	}
	
	public Minute(Date date)
	{
		this(toCalendar(date));
	}
	
	public Minute(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Minute(Calendar calendar)
	{
		super(calendar);
		setSecond(0);
		setMillisecond(0);
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
	
	public static Minute fromString(String value)
	{
		return new Minute(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Minute parse(String value, String pattern)
	{
		return new Minute(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		if(getMinute() != other.getMinute())
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Minute clone()
	{
		return new Minute((Calendar) calendar.clone());
	}
	
}
