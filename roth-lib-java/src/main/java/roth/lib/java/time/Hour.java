package roth.lib.java.time;

import java.text.SimpleDateFormat;
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
	
	public Hour addYears(int years)
	{
		super.addYears(years);
		return this;
	}
	
	public Hour subtractYears(int years)
	{
		super.subtractYears(years);
		return this;
	}
	
	public Hour addMonths(int months)
	{
		super.addMonths(months);
		return this;
	}
	
	public Hour subtractMonths(int months)
	{
		super.subtractMonths(months);
		return this;
	}
	
	public Hour addDays(int days)
	{
		super.addDays(days);
		return this;
	}
	
	public Hour subtractDays(int days)
	{
		super.subtractDays(days);
		return this;
	}
	
	public Hour addHours(int hours)
	{
		super.addHours(hours);
		return this;
	}
	
	public Hour subtractHours(int hours)
	{
		super.subtractHours(hours);
		return this;
	}
	
	public Hour addMinutes(int minutes)
	{
		super.addMinutes(minutes);
		return this;
	}
	
	public Hour subtractMinutes(int minutes)
	{
		super.subtractMinutes(minutes);
		return this;
	}
	
	public Hour addSeconds(int seconds)
	{
		super.addSeconds(seconds);
		return this;
	}
	
	public Hour subtractSeconds(int seconds)
	{
		super.subtractSeconds(seconds);
		return this;
	}
	
	public Hour addMilliseconds(int milliseconds)
	{
		super.addMilliseconds(milliseconds);
		return this;
	}
	
	public Hour subtractMilliseconds(int milliseconds)
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
	
	public static Hour fromString(String value)
	{
		return new Hour(parseCalendar(value, DEFAULT_PATTERN));
	}
	
	public static Hour parse(String value, String pattern)
	{
		return new Hour(parseCalendar(value, pattern != null ? pattern : DEFAULT_PATTERN));
	}
	
	@Override
	public Hour clone()
	{
		return new Hour((Calendar) calendar.clone());
	}
	
}
