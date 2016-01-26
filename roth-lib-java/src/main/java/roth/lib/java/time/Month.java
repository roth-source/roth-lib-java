package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Month extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM";
	
	public Month()
	{
		this(new GregorianCalendar());
	}
	
	public Month(int year, int month)
	{
		this();
		setYear(year);
		setMonth(month);
	}

	public Month(Date date)
	{
		this(toCalendar(date));
	}
	
	public Month(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Month(Calendar calendar)
	{
		super(calendar);
		setDay(1);
		setHour(0);
		setMinute(0);
		setSecond(0);
		setMillisecond(0);
	}
	
	public Month addYear(int year)
	{
		super.addYear(year);
		return this;
	}
	
	public Month subtractYear(int year)
	{
		super.subtractYear(year);
		return this;
	}
	
	public Month addMonth(int month)
	{
		super.addMonth(month);
		return this;
	}
	
	public Month subtractMonth(int month)
	{
		super.subtractMonth(month);
		return this;
	}
	
	public Month addDay(int day)
	{
		super.addDay(day);
		return this;
	}
	
	public Month subtractDay(int day)
	{
		super.subtractDay(day);
		return this;
	}
	
	public Month addHour(int hour)
	{
		super.addHour(hour);
		return this;
	}
	
	public Month subtractHour(int hour)
	{
		super.subtractHour(hour);
		return this;
	}
	
	public Month addMinute(int minute)
	{
		super.addMinute(minute);
		return this;
	}
	
	public Month subtractMinute(int minute)
	{
		super.subtractMinute(minute);
		return this;
	}
	
	public Month addSecond(int second)
	{
		super.addSecond(second);
		return this;
	}
	
	public Month subtractSecond(int second)
	{
		super.subtractSecond(second);
		return this;
	}
	
	public Month addMillisecond(int millisecond)
	{
		super.addMillisecond(millisecond);
		return this;
	}
	
	public Month subtractMillisecond(int millisecond)
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
	
	public static Month fromString(String value)
	{
		return new Month(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Month parse(String value, String pattern)
	{
		return new Month(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
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
		return true;
	}
	
	@Override
	public Month clone()
	{
		return new Month((Calendar) calendar.clone());
	}
	
}
