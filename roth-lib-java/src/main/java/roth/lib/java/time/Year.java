package roth.lib.java.time;

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
	public Year setYear(int year)
	{
		super.setYear(year);
		return this;
	}
	
	@Override
	public Year setMonth(int month)
	{
		super.setMonth(month);
		return this;
	}
	
	@Override
	public Year setDay(int day)
	{
		super.setDay(day);
		return this;
	}
	
	@Override
	public Year setHour(int hour)
	{
		super.setHour(hour);
		return this;
	}
	
	@Override
	public Year setMinute(int minute)
	{
		super.setMinute(minute);
		return this;
	}
	
	@Override
	public Year setSecond(int second)
	{
		super.setSecond(second);
		return this;
	}
	
	@Override
	public Year setMillisecond(int millisecond)
	{
		super.setMillisecond(millisecond);
		return this;
	}
	
	@Override
	public Year addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	@Override
	public Year subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	@Override
	public Year addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	@Override
	public Year subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	@Override
	public Year addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	@Override
	public Year subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	@Override
	public Year addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	@Override
	public Year subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	@Override
	public Year addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	@Override
	public Year subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	@Override
	public Year addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	@Override
	public Year subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	@Override
	public Year addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public Year subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public String getDefaultPattern()
	{
		return DEFAULT_PATTERN;
	}
	
	public static Year parse(String value)
	{
		return parse(value, TimeZone.DEFAULT);
	}
	
	public static Year parse(String value, TimeZone timeZone)
	{
		return parse(value, timeZone, DEFAULT_PATTERN);
	}
	
	public static Year parse(String value, String pattern)
	{
		return parse(value, TimeZone.DEFAULT, pattern);
	}
	
	public static Year parse(String value, TimeZone timeZone, String pattern)
	{
		return new Year(parseCalendar(value, timeZone, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Year clone()
	{
		return new Year((Calendar) calendar.clone());
	}
	
}
