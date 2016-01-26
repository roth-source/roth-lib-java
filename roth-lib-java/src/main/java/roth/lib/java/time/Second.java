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
	
	public Second(int second)
	{
		this();
		setSecond(second);
	}
	
	public Second(int minute, int second)
	{
		this();
		setMinute(minute);
		setSecond(second);
	}
	
	public Second(int hour, int minute, int second)
	{
		this();
		setHour(hour);
		setMinute(minute);
		setSecond(second);
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
	
	public Second addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Second subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Second addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Second subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Second addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Second subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Second addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Second subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Second addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Second subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Second addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Second subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Second addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	public Second subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
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
