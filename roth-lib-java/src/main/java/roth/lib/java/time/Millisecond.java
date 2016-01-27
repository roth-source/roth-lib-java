package roth.lib.java.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Millisecond extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public Millisecond()
	{
		super();
	}
	
	public Millisecond(int millisecond)
	{
		this();
		setMillisecond(millisecond);
	}
	
	public Millisecond(int second, int millisecond)
	{
		this();
		setSecond(second);
		setMillisecond(millisecond);
	}
	
	public Millisecond(int minute, int second, int millisecond)
	{
		this();
		setMinute(minute);
		setSecond(second);
		setMillisecond(millisecond);
	}
	
	public Millisecond(int hour, int minute, int second, int millisecond)
	{
		this();
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setMillisecond(millisecond);
	}
	
	public Millisecond(int year, int month, int day, int hour, int minute, int second, int millisecond)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setMillisecond(millisecond);
	}
	
	public Millisecond(Date date)
	{
		this(toCalendar(date));
	}
	
	public Millisecond(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Millisecond(Calendar calendar)
	{
		super(calendar);
	}
	
	public Millisecond addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Millisecond subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Millisecond addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Millisecond subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Millisecond addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Millisecond subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Millisecond addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Millisecond subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Millisecond addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Millisecond subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Millisecond addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Millisecond subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Millisecond addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	public Millisecond subtractMilliseconds(int milliseconds)
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
	
	public static Millisecond fromString(String value)
	{
		return new Millisecond(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Millisecond parse(String value, String pattern)
	{
		return new Millisecond(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Millisecond clone()
	{
		return new Millisecond((Calendar) calendar.clone());
	}
	
}
