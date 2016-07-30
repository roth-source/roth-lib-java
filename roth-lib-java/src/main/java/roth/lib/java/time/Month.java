package roth.lib.java.time;

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
	
	public Month(int month)
	{
		this();
		setMonth(month);
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
	public Month setYear(int year)
	{
		super.setYear(year);
		return this;
	}
	
	@Override
	public Month setMonth(int month)
	{
		super.setMonth(month);
		return this;
	}
	
	@Override
	public Month setDay(int day)
	{
		super.setDay(day);
		return this;
	}
	
	@Override
	public Month setHour(int hour)
	{
		super.setHour(hour);
		return this;
	}
	
	@Override
	public Month setMinute(int minute)
	{
		super.setMinute(minute);
		return this;
	}
	
	@Override
	public Month setSecond(int second)
	{
		super.setSecond(second);
		return this;
	}
	
	@Override
	public Month setMillisecond(int millisecond)
	{
		super.setMillisecond(millisecond);
		return this;
	}
	
	@Override
	public Month addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	@Override
	public Month subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	@Override
	public Month addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	@Override
	public Month subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	@Override
	public Month addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	@Override
	public Month subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	@Override
	public Month addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	@Override
	public Month subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	@Override
	public Month addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	@Override
	public Month subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	@Override
	public Month addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	@Override
	public Month subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	@Override
	public Month addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public Month subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public String getDefaultPattern()
	{
		return DEFAULT_PATTERN;
	}
	
	public static Month parse(String value)
	{
		return parse(value, TimeZone.DEFAULT);
	}
	
	public static Month parse(String value, TimeZone timeZone)
	{
		return parse(value, timeZone, DEFAULT_PATTERN);
	}
	
	public static Month parse(String value, String pattern)
	{
		return parse(value, TimeZone.DEFAULT, pattern);
	}
	
	public static Month parse(String value, TimeZone timeZone, String pattern)
	{
		return new Month(parseCalendar(value, timeZone, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Month clone()
	{
		return new Month((Calendar) calendar.clone());
	}
	
}
