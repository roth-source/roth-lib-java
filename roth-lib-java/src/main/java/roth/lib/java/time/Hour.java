package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Hour extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH";
	
	public Hour()
	{
		this(new GregorianCalendar());
	}
	
	public Hour(int year, int month, int day, int hour)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
	}
	
	public Hour(Date date)
	{
		this(toCalendar(date));
	}
	
	public Hour(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Hour(Calendar calendar)
	{
		super(calendar);
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
	
	public static Hour fromString(String value)
	{
		return new Hour(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Hour parse(String value, String pattern)
	{
		return new Hour(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		return true;
	}
	
}
