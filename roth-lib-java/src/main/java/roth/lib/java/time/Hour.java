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
	
	public Hour addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Hour subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Hour addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Hour subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Hour addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Hour subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Hour addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Hour subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Hour addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Hour subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Hour addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Hour subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Hour addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Hour subtractMillisecond(int millisecond)
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
	
	@Override
	public Hour clone()
	{
		return new Hour((Calendar) calendar.clone());
	}
	
}
