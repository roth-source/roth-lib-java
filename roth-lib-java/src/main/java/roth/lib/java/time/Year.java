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
	protected Year clone()
	{
		return new Year((Calendar) calendar.clone());
	}
	
}
