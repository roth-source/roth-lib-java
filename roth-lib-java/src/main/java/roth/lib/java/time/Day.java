package roth.lib.java.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Day extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	public Day()
	{
		this(new GregorianCalendar());
	}
	
	public Day(int day)
	{
		this();
		setDay(day);
	}
	
	public Day(int month, int day)
	{
		this();
		setMonth(month);
		setDay(day);
	}
	
	public Day(int year, int month, int day)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	public Day(Date date)
	{
		this(toCalendar(date));
	}
	
	public Day(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Day(Calendar calendar)
	{
		super(calendar);
		setHour(0);
		setMinute(0);
		setSecond(0);
		setMillisecond(0);
	}
	
	public Day addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Day subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Day addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Day subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Day addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Day subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Day addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Day subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Day addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Day subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Day addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Day subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Day addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	public Day subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
		return this;
	}
	
	public Day getBusinessDay()
	{
		while(isWeekendOrHoliday())
		{
			addDays(1);
		}
		return this;
	}
	
	@Override
	public String getDefaultPattern()
	{
		return DEFAULT_PATTERN;
	}
	
	public static Day parse(String value)
	{
		return parse(value, TimeZone.DEFAULT);
	}
	
	public static Day parse(String value, TimeZone timeZone)
	{
		return parse(value, timeZone, DEFAULT_PATTERN);
	}
	
	public static Day parse(String value, String pattern)
	{
		return parse(value, TimeZone.DEFAULT, pattern);
	}
	
	public static Day parse(String value, TimeZone timeZone, String pattern)
	{
		return new Day(parseCalendar(value, timeZone, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Day clone()
	{
		return new Day((Calendar) calendar.clone());
	}
	
}
