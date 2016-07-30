package roth.lib.java.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Hour extends Time
{
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH";
	
	public Hour()
	{
		this(new GregorianCalendar());
	}
	
	public Hour(int hour)
	{
		this();
		setHour(hour);
	}
	
	public Hour(int year, int month, int day, int hour)
	{
		this();
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
	}
	
	public Hour(Date date)
	{
		this(toCalendar(date));
	}
	
	public Hour(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Hour(Calendar calendar)
	{
		super(calendar);
		setMinute(0);
		setSecond(0);
		setMillisecond(0);
	}
	
	@Override
	public Hour setYear(int year)
	{
		super.setYear(year);
		return this;
	}
	
	@Override
	public Hour setMonth(int month)
	{
		super.setMonth(month);
		return this;
	}
	
	@Override
	public Hour setDay(int day)
	{
		super.setDay(day);
		return this;
	}
	
	@Override
	public Hour setHour(int hour)
	{
		super.setHour(hour);
		return this;
	}
	
	@Override
	public Hour setMinute(int minute)
	{
		super.setMinute(minute);
		return this;
	}
	
	@Override
	public Hour setSecond(int second)
	{
		super.setSecond(second);
		return this;
	}
	
	@Override
	public Hour setMillisecond(int millisecond)
	{
		super.setMillisecond(millisecond);
		return this;
	}
	
	@Override
	public Hour addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	@Override
	public Hour subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	@Override
	public Hour addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	@Override
	public Hour subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	@Override
	public Hour addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	@Override
	public Hour subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	@Override
	public Hour addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	@Override
	public Hour subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	@Override
	public Hour addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	@Override
	public Hour subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	@Override
	public Hour addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	@Override
	public Hour subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	@Override
	public Hour addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public Hour subtractMilliseconds(int milliseconds)
	{
		super.subtractMilliseconds(milliseconds);
		return this;
	}
	
	@Override
	public String getDefaultPattern()
	{
		return DEFAULT_PATTERN;
	}
	
	public static Hour parse(String value)
	{
		return parse(value, TimeZone.DEFAULT);
	}
	
	public static Hour parse(String value, TimeZone timeZone)
	{
		return parse(value, timeZone, DEFAULT_PATTERN);
	}
	
	public static Hour parse(String value, String pattern)
	{
		return parse(value, TimeZone.DEFAULT, pattern);
	}
	
	public static Hour parse(String value, TimeZone timeZone, String pattern)
	{
		return new Hour(parseCalendar(value, timeZone, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Hour clone()
	{
		return new Hour((Calendar) calendar.clone());
	}
	
}
