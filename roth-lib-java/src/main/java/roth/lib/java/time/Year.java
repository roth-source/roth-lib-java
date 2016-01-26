package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Year extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy";
	
	public Year()
	{
		this(new GregorianCalendar());
	}
	
	public Year(int year)
	{
		this();
		setYear(year);
	}
	
	public Year(Date date)
	{
		this(toCalendar(date));
	}
	
	public Year(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Year(Calendar calendar)
	{
		super(calendar);
		setMonth(1);
		setDay(1);
		setHour(0);
		setMinute(0);
		setSecond(0);
		setMillisecond(0);
	}
	
	public Year addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Year subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Year addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Year subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Year addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Year subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Year addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Year subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Year addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Year subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Year addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Year subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Year addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Year subtractMillisecond(int millisecond)
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
	
	public static Year fromString(String value)
	{
		return new Year(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Year parse(String value, String pattern)
	{
		return new Year(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		return true;
	}
	
	@Override
	public Year clone()
	{
		return new Year((Calendar) calendar.clone());
	}
	
}
