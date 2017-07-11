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
	
	public Day(TimeZone timeZone)
	{
		this();
		setTimeZone(timeZone);
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
	
	@Override
	public Day setYear(int year)
	{
		super.setYear(year);
		return this;
	}
	
	@Override
	public Day setMonth(int month)
	{
		super.setMonth(month);
		return this;
	}
	
	@Override
	public Day setDay(int day)
	{
		super.setDay(day);
		return this;
	}
	
	@Override
	public Day setHour(int hour)
	{
		super.setHour(hour);
		return this;
	}
	
	@Override
	public Day setMinute(int minute)
	{
		super.setMinute(minute);
		return this;
	}
	
	@Override
	public Day setSecond(int second)
	{
		super.setSecond(second);
		return this;
	}
	
	@Override
	public Day setMillisecond(int millisecond)
	{
		super.setMillisecond(millisecond);
		return this;
	}
	
	@Override
	public Day addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	@Override
	public Day subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	@Override
	public Day addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	@Override
	public Day subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	@Override
	public Day addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	@Override
	public Day subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	@Override
	public Day addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	@Override
	public Day subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	@Override
	public Day addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	@Override
	public Day subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	@Override
	public Day addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	@Override
	public Day subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	@Override
	public Day addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	@Override
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
