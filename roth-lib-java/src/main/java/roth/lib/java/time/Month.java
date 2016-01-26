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
	protected Month clone()
	{
		return new Month((Calendar) calendar.clone());
	}
	
}
