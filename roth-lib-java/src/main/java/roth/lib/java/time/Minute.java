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
	
	public Minute addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Minute subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Minute addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Minute subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Minute addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Minute subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Minute addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Minute subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Minute addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Minute subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Minute addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Minute subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Minute addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
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
