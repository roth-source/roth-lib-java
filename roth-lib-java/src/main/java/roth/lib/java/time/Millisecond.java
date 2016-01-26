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
	protected Millisecond clone()
	{
		return new Millisecond((Calendar) calendar.clone());
	}
	
}
