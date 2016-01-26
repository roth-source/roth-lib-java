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
	
	public Minute(int minute)
	{
		this();
		setMinute(minute);
	}
	
	public Minute(int hour, int minute)
	{
		this();
		setHour(hour);
		setMinute(minute);
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
	
	public Minute addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Minute subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Minute addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Minute subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Minute addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Minute subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Minute addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Minute subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Minute addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Minute subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Minute addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Minute subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Minute addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Minute subtractMillisecond(int millisecond)
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
