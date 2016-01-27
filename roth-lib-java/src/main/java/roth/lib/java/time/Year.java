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
	
	public Year addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Year subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Year addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Year subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Year addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Year subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Year addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Year subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Year addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Year subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Year addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Year subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Year addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	public Year subtractMilliseconds(int milliseconds)
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
	
	public static Year fromString(String value)
	{
		return new Year(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Year parse(String value, String pattern)
	{
		return new Year(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Year clone()
	{
		return new Year((Calendar) calendar.clone());
	}
	
}
