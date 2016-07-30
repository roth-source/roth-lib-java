package roth.lib.java.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Minute extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";
	
	public Minute()
	{
		this(new GregorianCalendar());
	}
	
	public Minute(int minute)
	{
		this();
		setMinute(minute);
	}
	
	public Minute(int hour, int minute)
	{
		this();
		setHour(hour);
		setMinute(minute);
	}
	
	public Minute(int year, int month, int day, int hour, int minute)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
	}
	
	public Minute(Date date)
	{
		this(toCalendar(date));
	}
	
	public Minute(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Minute(Calendar calendar)
	{
		super(calendar);
		setSecond(0);
		setMillisecond(0);
	}
	
	@Override
	public Minute setYear(int year)
	{
		super.setYear(year);
		return this;
	}
	
	@Override
	public Minute setMonth(int month)
	{
		super.setMonth(month);
		return this;
	}
	
	@Override
	public Minute setDay(int day)
	{
		super.setDay(day);
		return this;
	}
	
	@Override
	public Minute setHour(int hour)
	{
		super.setHour(hour);
		return this;
	}
	
	@Override
	public Minute setMinute(int minute)
	{
		super.setMinute(minute);
		return this;
	}
	
	@Override
	public Minute setSecond(int second)
	{
		super.setSecond(second);
		return this;
	}
	
	@Override
	public Minute setMillisecond(int millisecond)
	{
		super.setMillisecond(millisecond);
		return this;
	}
	
	@Override
	public Minute addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	@Override
	public Minute subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	@Override
	public Minute addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	@Override
	public Minute subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	@Override
	public Minute addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	@Override
	public Minute subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	@Override
	public Minute addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	@Override
	public Minute subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	@Override
	public Minute addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	@Override
	public Minute subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	@Override
	public Minute addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	@Override
	public Minute subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	@Override
	public Minute addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public Minute subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public String getDefaultPattern()
	{
		return DEFAULT_PATTERN;
	}
	
	public static Minute parse(String value)
	{
		return parse(value, TimeZone.DEFAULT);
	}
	
	public static Minute parse(String value, TimeZone timeZone)
	{
		return parse(value, timeZone, DEFAULT_PATTERN);
	}
	
	public static Minute parse(String value, String pattern)
	{
		return parse(value, TimeZone.DEFAULT, pattern);
	}
	
	public static Minute parse(String value, TimeZone timeZone, String pattern)
	{
		return new Minute(parseCalendar(value, timeZone, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Minute clone()
	{
		return new Minute((Calendar) calendar.clone());
	}
	
}
