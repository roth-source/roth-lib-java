package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Millisecond extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public Millisecond()
	{
		super();
	}
	
	public Millisecond(int year, int month, int day, int hour, int minute, int second, int millisecond)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	
	public Millisecond(Date date)
	{
		this(toCalendar(date));
	}
	
	public Millisecond(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Millisecond(Calendar calendar)
	{
		super(calendar);
	}
	
	public Millisecond addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Millisecond subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Millisecond addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Millisecond subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Millisecond addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Millisecond subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Millisecond addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Millisecond subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Millisecond addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Millisecond subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Millisecond addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Millisecond subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Millisecond addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Millisecond subtractMillisecond(int millisecond)
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
	
	public static Millisecond fromString(String value)
	{
		return new Millisecond(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Millisecond parse(String value, String pattern)
	{
		return new Millisecond(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		if(getMillisecond() != other.getMillisecond())
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Millisecond clone()
	{
		return new Millisecond((Calendar) calendar.clone());
	}
	
}
