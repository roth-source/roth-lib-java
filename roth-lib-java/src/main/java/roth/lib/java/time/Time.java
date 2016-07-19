package roth.lib.java.time;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class Time implements Serializable, Comparable<Time>, Cloneable
{
	public static List<Day> HOLIDAYS = new List<Day>();
	
	static
	{
		// FIND LIST AT
		// http://www.federalreserve.gov/aboutthefed/k8.htm
		
		// 2015 Holidays
		HOLIDAYS.add(new Day(2015,  1,  1)); // New Year's Day
		HOLIDAYS.add(new Day(2015,  1, 19)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(new Day(2015,  2, 16)); // Washington's Birthday
		HOLIDAYS.add(new Day(2015,  5, 25)); // Memorial Day
		HOLIDAYS.add(new Day(2015,  7,  4)); // Independence Day
		HOLIDAYS.add(new Day(2015,  9,  7)); // Labor Day
		HOLIDAYS.add(new Day(2015, 10, 12)); // Columbus Day
		HOLIDAYS.add(new Day(2015, 11, 11)); // Veterans Day
		HOLIDAYS.add(new Day(2015, 11, 26)); // Thanksgiving Day
		HOLIDAYS.add(new Day(2015, 12, 25)); // Christmas Day
		
		// 2016 Holidays
		HOLIDAYS.add(new Day(2016,  1,  1)); // New Year's Day
		HOLIDAYS.add(new Day(2016,  1, 18)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(new Day(2016,  2, 15)); // Washington's Birthday
		HOLIDAYS.add(new Day(2016,  5, 30)); // Memorial Day
		HOLIDAYS.add(new Day(2016,  7,  4)); // Independence Day
		HOLIDAYS.add(new Day(2016,  9,  5)); // Labor Day
		HOLIDAYS.add(new Day(2016, 10, 10)); // Columbus Day
		HOLIDAYS.add(new Day(2016, 11, 11)); // Veterans Day
		HOLIDAYS.add(new Day(2016, 11, 24)); // Thanksgiving Day
		HOLIDAYS.add(new Day(2016, 12, 25)); // Christmas Day
		
		// 2017 Holidays
		HOLIDAYS.add(new Day(2017,  1,  2)); // New Year's Day
		HOLIDAYS.add(new Day(2017,  1, 16)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(new Day(2017,  2, 20)); // Washington's Birthday
		HOLIDAYS.add(new Day(2017,  5, 29)); // Memorial Day
		HOLIDAYS.add(new Day(2017,  7,  4)); // Independence Day
		HOLIDAYS.add(new Day(2017,  9,  4)); // Labor Day
		HOLIDAYS.add(new Day(2017, 10,  9)); // Columbus Day
		HOLIDAYS.add(new Day(2017, 11, 11)); // Veterans Day
		HOLIDAYS.add(new Day(2017, 11, 23)); // Thanksgiving Day
		HOLIDAYS.add(new Day(2017, 12, 25)); // Christmas Day
		
		// 2018 Holidays
		HOLIDAYS.add(new Day(2018,  1,  1)); // New Year's Day
		HOLIDAYS.add(new Day(2018,  1, 15)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(new Day(2018,  2, 19)); // Washington's Birthday
		HOLIDAYS.add(new Day(2018,  5, 28)); // Memorial Day
		HOLIDAYS.add(new Day(2018,  7,  4)); // Independence Day
		HOLIDAYS.add(new Day(2018,  9,  3)); // Labor Day
		HOLIDAYS.add(new Day(2018, 10,  8)); // Columbus Day
		HOLIDAYS.add(new Day(2018, 11, 12)); // Veterans Day
		HOLIDAYS.add(new Day(2018, 11, 22)); // Thanksgiving Day
		HOLIDAYS.add(new Day(2018, 12, 25)); // Christmas Day
		
		// 2019 Holidays
		HOLIDAYS.add(new Day(2019,  1,  1)); // New Year's Day
		HOLIDAYS.add(new Day(2019,  1, 21)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(new Day(2019,  2, 18)); // Washington's Birthday
		HOLIDAYS.add(new Day(2019,  5, 27)); // Memorial Day
		HOLIDAYS.add(new Day(2019,  7,  4)); // Independence Day
		HOLIDAYS.add(new Day(2019,  9,  2)); // Labor Day
		HOLIDAYS.add(new Day(2019, 10, 14)); // Columbus Day
		HOLIDAYS.add(new Day(2019, 11, 11)); // Veterans Day
		HOLIDAYS.add(new Day(2019, 11, 28)); // Thanksgiving Day
		HOLIDAYS.add(new Day(2019, 12, 25)); // Christmas Day
	}
	
	protected Calendar calendar;
	
	public Time()
	{
		this(new GregorianCalendar());
	}
	
	public Time(Date date)
	{
		this(toCalendar(date));
	}
	
	public Time(long timestamp)
	{
		this(toCalendar(timestamp));
	}
	
	public Time(Calendar calendar)
	{
		this.calendar = calendar;
	}
	
	public Time(int year, int month, int day, int hour, int minute, int second, int millisecond)
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
	
	protected static Calendar toCalendar(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	protected static Calendar toCalendar(long timestamp)
	{
		return toCalendar(new Date(timestamp));
	}
	
	public int getYear()
	{
		return calendar.get(Calendar.YEAR);
	}
	
	public int getMonth()
	{
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public int getDay()
	{
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getHour()
	{
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute()
	{
		return calendar.get(Calendar.MINUTE);
	}
	
	public int getSecond()
	{
		return calendar.get(Calendar.SECOND);
	}
	
	public int getMillisecond()
	{
		return calendar.get(Calendar.MILLISECOND);
	}
	
	public int getWeekday()
	{
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public Time setYear(int year)
	{
		calendar.set(Calendar.YEAR, year);
		return this;
	}
	
	public Time setMonth(int month)
	{
		if(1 <= month && month <= 12)
		{
			calendar.set(Calendar.MONTH, month - 1);
			return this;
		}
		else
		{
			throw new TimeException("invalid month value " + month);
		}
	}
	
	public Time setDay(int day)
	{
		if(1 <= day && day <= 31)
		{
			calendar.set(Calendar.DAY_OF_MONTH, day);
			return this;
		}
		else
		{
			throw new TimeException("invalid day value " + day);
		}
	}
	
	public Time setHour(int hour)
	{
		if(0 <= hour && hour <= 23)
		{
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			return this;
		}
		else
		{
			throw new TimeException("invalid hour value " + hour);
		}
	}
	
	public Time setMinute(int minute)
	{
		if(0 <= minute && minute <= 60)
		{
			calendar.set(Calendar.MINUTE, minute);
			return this;
		}
		else
		{
			throw new TimeException("invalid minute value " + minute);
		}
	}
	
	public Time setSecond(int second)
	{
		if(0 <= second && second <= 60)
		{
			calendar.set(Calendar.SECOND, second);
			return this;
		}
		else
		{
			throw new TimeException("invalid second value " + second);
		}
	}
	
	public Time setMillisecond(int millisecond)
	{
		if(0 <= millisecond && millisecond <= 999)
		{
			calendar.set(Calendar.MILLISECOND, millisecond);
			return this;
		}
		else
		{
			throw new TimeException("invalid millisecond value " + millisecond);
		}
	}
	
	public int getMonthDays()
	{
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public Time addYears(int years)
	{
		calendar.add(Calendar.YEAR, years);
		return this;
	}
	
	public Time subtractYears(int years)
	{
		calendar.add(Calendar.YEAR, years * -1);
		return this;
	}
	
	public Time addMonths(int months)
	{
		calendar.add(Calendar.MONTH, months);
		return this;
	}
	
	public Time subtractMonths(int months)
	{
		calendar.add(Calendar.MONTH, months * -1);
		return this;
	}
	
	public Time addDays(int days)
	{
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return this;
	}
	
	public Time subtractDays(int days)
	{
		calendar.add(Calendar.DAY_OF_MONTH, days * -1);
		return this;
	}
	
	public Time addHours(int hours)
	{
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return this;
	}
	
	public Time subtractHours(int hours)
	{
		calendar.add(Calendar.HOUR_OF_DAY, hours * -1);
		return this;
	}
	
	public Time addMinutes(int minutes)
	{
		calendar.add(Calendar.MINUTE, minutes);
		return this;
	}
	
	public Time subtractMinutes(int minutes)
	{
		calendar.add(Calendar.MINUTE, minutes * -1);
		return this;
	}
	
	public Time addSeconds(int seconds)
	{
		calendar.add(Calendar.SECOND, seconds);
		return this;
	}
	
	public Time subtractSeconds(int seconds)
	{
		calendar.add(Calendar.SECOND, seconds * -1);
		return this;
	}
	
	public Time addMilliseconds(int milliseconds)
	{
		calendar.add(Calendar.MILLISECOND, milliseconds);
		return this;
	}
	
	public Time subtractMilliseconds(int milliseconds)
	{
		calendar.add(Calendar.MILLISECOND, milliseconds * -1);
		return this;
	}
	
	public Calendar toCalendar()
	{
		return calendar;
	}
	
	public Date toDate()
	{
		return calendar.getTime();
	}
	
	public long toTimestamp()
	{
		return calendar.getTimeInMillis();
	}
	
	public java.sql.Date toSqlDate()
	{
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	public java.sql.Time toSqlTime()
	{
		return new java.sql.Time(calendar.getTimeInMillis());
	}
	
	public java.sql.Timestamp toSqlTimestamp()
	{
		return new java.sql.Timestamp(calendar.getTimeInMillis());
	}
	
	public Year toYear()
	{
		return new Year(calendar);
	}
	
	public Month toMonth()
	{
		return new Month(calendar);
	}
	
	public Day toDay()
	{
		return new Day(calendar);
	}
	
	public Hour toHour()
	{
		return new Hour(calendar);
	}
	
	public Minute toMinute()
	{
		return new Minute(calendar);
	}
	
	public Second toSecond()
	{
		return new Second(calendar);
	}
	
	public Millisecond toMillisecond()
	{
		return new Millisecond(calendar);
	}
	
	public static Time parse(String value, String pattern)
	{
		return new Time(parseCalendar(value, pattern));
	}
	
	public static Calendar parseCalendar(String value, String pattern)
	{
		Calendar calendar = null;
		try
		{
			Date date = new SimpleDateFormat(pattern).parse(value);
			if(date != null)
			{
				calendar = new GregorianCalendar();
				calendar.setTime(date);
			}
		}
		catch(Exception e)
		{
			
		}
		return calendar;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(toTimestamp());
	}
	
	public String format(String pattern)
	{
		return new SimpleDateFormat(pattern).format(toDate());
	}
	
	public String format(String pattern, String timeZone)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		return simpleDateFormat.format(toDate());
	}
	
	public boolean isWeekend()
	{
		int dayOfWeek = toCalendar().get(Calendar.DAY_OF_WEEK);
		return Calendar.SATURDAY == dayOfWeek || Calendar.SUNDAY == dayOfWeek;
	}
	
	public boolean isHoliday()
	{
		return HOLIDAYS.contains(toDay());
	}
	
	public boolean isWeekendOrHoliday()
	{
		return isWeekend() || isHoliday();
	}
	
	public boolean isBusinessDay()
	{
		return !isWeekendOrHoliday();
	}
	
	public boolean isAfterNow()
	{
		return isAfter(new Time());
	}
	
	public boolean isAfter(Time time)
	{
		return time != null ? calendar.after(time.calendar) : false;
	}
	
	public boolean isBeforeNow()
	{
		return isBefore(new Time());
	}
	
	public boolean isBefore(Time time)
	{
		return time != null ? calendar.before(time.calendar) : false;
	}
	
	@Override
	public int compareTo(Time time)
	{
		return time != null ? calendar.compareTo(time.calendar) : -1;
	}
	
	@Override
	public int hashCode()
	{
		return calendar.hashCode();
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object != null && object instanceof Time)
		{
			return calendar.equals(((Time) object).calendar);
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public Time clone()
	{
		return new Time((Calendar) calendar.clone());
	}
	
}
