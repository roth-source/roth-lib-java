package roth.lib.java.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class CalendarUtil
{
	public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String TIMEZONE_FORMAT = "yyyy-MM-dd HH:mm:ss z";
	public static String DISPLAY_FORMAT = "d MMM yyyy HH:mm:ss z";
	public static String DAY_FORMAT = "yyyy-MM-dd";
	public static String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static LinkedList<Calendar> HOLIDAYS = new LinkedList<Calendar>();
	
	static
	{
		// FIND LIST AT
		// http://www.federalreserve.gov/aboutthefed/k8.htm
		
		// 2015 Holidays
		HOLIDAYS.add(get(2015,  1,  1)); // New Year's Day
		HOLIDAYS.add(get(2015,  1, 19)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(get(2015,  2, 16)); // Washington's Birthday
		HOLIDAYS.add(get(2015,  5, 25)); // Memorial Day
		HOLIDAYS.add(get(2015,  7,  4)); // Independence Day
		HOLIDAYS.add(get(2015,  9,  7)); // Labor Day
		HOLIDAYS.add(get(2015, 10, 12)); // Columbus Day
		HOLIDAYS.add(get(2015, 11, 11)); // Veterans Day
		HOLIDAYS.add(get(2015, 11, 26)); // Thanksgiving Day
		HOLIDAYS.add(get(2015, 12, 25)); // Christmas Day
		
		// 2016 Holidays
		HOLIDAYS.add(get(2016,  1,  1)); // New Year's Day
		HOLIDAYS.add(get(2016,  1, 18)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(get(2016,  2, 15)); // Washington's Birthday
		HOLIDAYS.add(get(2016,  5, 30)); // Memorial Day
		HOLIDAYS.add(get(2016,  7,  4)); // Independence Day
		HOLIDAYS.add(get(2016,  9,  5)); // Labor Day
		HOLIDAYS.add(get(2016, 10, 10)); // Columbus Day
		HOLIDAYS.add(get(2016, 11, 11)); // Veterans Day
		HOLIDAYS.add(get(2016, 11, 24)); // Thanksgiving Day
		HOLIDAYS.add(get(2016, 12, 25)); // Christmas Day
		
		// 2017 Holidays
		HOLIDAYS.add(get(2017,  1,  2)); // New Year's Day
		HOLIDAYS.add(get(2017,  1, 16)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(get(2017,  2, 20)); // Washington's Birthday
		HOLIDAYS.add(get(2017,  5, 29)); // Memorial Day
		HOLIDAYS.add(get(2017,  7,  4)); // Independence Day
		HOLIDAYS.add(get(2017,  9,  4)); // Labor Day
		HOLIDAYS.add(get(2017, 10,  9)); // Columbus Day
		HOLIDAYS.add(get(2017, 11, 11)); // Veterans Day
		HOLIDAYS.add(get(2017, 11, 23)); // Thanksgiving Day
		HOLIDAYS.add(get(2017, 12, 25)); // Christmas Day
		
		// 2018 Holidays
		HOLIDAYS.add(get(2018,  1,  1)); // New Year's Day
		HOLIDAYS.add(get(2018,  1, 15)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(get(2018,  2, 19)); // Washington's Birthday
		HOLIDAYS.add(get(2018,  5, 28)); // Memorial Day
		HOLIDAYS.add(get(2018,  7,  4)); // Independence Day
		HOLIDAYS.add(get(2018,  9,  3)); // Labor Day
		HOLIDAYS.add(get(2018, 10,  8)); // Columbus Day
		HOLIDAYS.add(get(2018, 11, 12)); // Veterans Day
		HOLIDAYS.add(get(2018, 11, 22)); // Thanksgiving Day
		HOLIDAYS.add(get(2018, 12, 25)); // Christmas Day
		
		// 2019 Holidays
		HOLIDAYS.add(get(2019,  1,  1)); // New Year's Day
		HOLIDAYS.add(get(2019,  1, 21)); // Martin Luther King, Jr. Day
		HOLIDAYS.add(get(2019,  2, 18)); // Washington's Birthday
		HOLIDAYS.add(get(2019,  5, 27)); // Memorial Day
		HOLIDAYS.add(get(2019,  7,  4)); // Independence Day
		HOLIDAYS.add(get(2019,  9,  2)); // Labor Day
		HOLIDAYS.add(get(2019, 10, 14)); // Columbus Day
		HOLIDAYS.add(get(2019, 11, 11)); // Veterans Day
		HOLIDAYS.add(get(2019, 11, 28)); // Thanksgiving Day
		HOLIDAYS.add(get(2019, 12, 25)); // Christmas Day
	}
	
	protected CalendarUtil()
	{
		
	}
	
	public static Calendar now()
	{
		return new GregorianCalendar();
	}
	
	public static Calendar get(int year, int month, int day)
	{
		return new GregorianCalendar(year, month - 1, day, 0, 0, 0);
	}
	
	public static Calendar get(int year, int month, int day, int hour, int minute, int second)
	{
		return new GregorianCalendar(year, month - 1, day, hour, minute, second);
	}
	
	public static Calendar get(Date time)
	{
		if(time != null)
		{
			Calendar calendar = now();
			calendar.setTime(time);
			return calendar;
		}
		return null;
	}
	
	public static Calendar get(Long time)
	{
		if(time != null)
		{
			Calendar calendar = now();
			calendar.setTimeInMillis(time);
			return calendar;
		}
		return null;
	}
	
	public static Timestamp getTimestamp()
	{
		return getTimestamp(now());
	}
	
	public static Timestamp getTimestamp(Calendar calendar)
	{
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static Calendar month()
	{
		return month(now(), false);
	}
	
	public static Calendar month(Calendar calendar)
	{
		return month(calendar, true);
	}
	
	public static Calendar month(Calendar calendar, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.set(Calendar.DAY_OF_MONTH, 1);
		clonedCalendar.set(Calendar.HOUR_OF_DAY, 0);
		clonedCalendar.set(Calendar.MINUTE, 0);
		clonedCalendar.set(Calendar.SECOND, 0);
		clonedCalendar.set(Calendar.MILLISECOND, 0);
		return clonedCalendar;
	}
	
	public static Calendar today()
	{
		return day(now(), false);
	}
	
	public static Calendar day(Calendar calendar)
	{
		return day(calendar, true);
	}
	
	public static Calendar day(Calendar calendar, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.set(Calendar.HOUR_OF_DAY, 0);
		clonedCalendar.set(Calendar.MINUTE, 0);
		clonedCalendar.set(Calendar.SECOND, 0);
		clonedCalendar.set(Calendar.MILLISECOND, 0);
		return clonedCalendar;
	}
	
	public static Calendar hour()
	{
		return hour(now(), false);
	}
	
	public static Calendar hour(Calendar calendar)
	{
		return hour(calendar, true);
	}
	
	public static Calendar hour(Calendar calendar, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.set(Calendar.MINUTE, 0);
		clonedCalendar.set(Calendar.SECOND, 0);
		clonedCalendar.set(Calendar.MILLISECOND, 0);
		return clonedCalendar;
	}
	
	public static Calendar minute()
	{
		return minute(now(), false);
	}
	
	public static Calendar minute(Calendar calendar)
	{
		return minute(calendar, true);
	}
	
	public static Calendar minute(Calendar calendar, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.set(Calendar.SECOND, 0);
		clonedCalendar.set(Calendar.MILLISECOND, 0);
		return clonedCalendar;
	}
	
	public static Calendar second()
	{
		return second(now(), false);
	}
	
	public static Calendar second(Calendar calendar)
	{
		return second(calendar, true);
	}
	
	public static Calendar second(Calendar calendar, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.set(Calendar.MILLISECOND, 0);
		return clonedCalendar;
	}
	
	public static Calendar addYears(Calendar calendar, int years)
	{
		return addYears(calendar, years, true);
	}
	
	public static Calendar addYears(Calendar calendar, int years, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.YEAR, years);
		return clonedCalendar;
	}
	
	public static Calendar addMonths(Calendar calendar, int months)
	{
		return addMonths(calendar, months, true);
	}
	
	public static Calendar addMonths(Calendar calendar, int months, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.MONTH, months);
		return clonedCalendar;
	}
	
	public static Calendar addDays(Calendar calendar, int days)
	{
		return addDays(calendar, days, true);
	}
	
	public static Calendar addDays(Calendar calendar, int days, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.DAY_OF_MONTH, days);
		return clonedCalendar;
	}
	
	public static Calendar addHours(Calendar calendar, int hours)
	{
		return addHours(calendar, hours, true);
	}
	
	public static Calendar addHours(Calendar calendar, int hours, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.HOUR_OF_DAY, hours);
		return clonedCalendar;
	}
	
	public static Calendar addMinutes(Calendar calendar, int minutes)
	{
		return addMinutes(calendar, minutes, true);
	}
	
	public static Calendar addMinutes(Calendar calendar, int minutes, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.MINUTE, minutes);
		return clonedCalendar;
	}
	
	public static Calendar addSeconds(Calendar calendar, int seconds)
	{
		return addSeconds(calendar, seconds, true);
	}
	
	public static Calendar addSeconds(Calendar calendar, int seconds, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.SECOND, seconds);
		return clonedCalendar;
	}

	public static Calendar subtractYears(Calendar calendar, int years)
	{
		return subtractYears(calendar, years, true);
	}
	
	public static Calendar subtractYears(Calendar calendar, int years, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.YEAR, years * -1);
		return clonedCalendar;
	}
	
	public static Calendar subtractMonths(Calendar calendar, int months)
	{
		return subtractMonths(calendar, months, true);
	}
	
	public static Calendar subtractMonths(Calendar calendar, int months, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.MONTH, months * -1);
		return clonedCalendar;
	}
	
	public static Calendar subtractDays(Calendar calendar, int days)
	{
		return subtractDays(calendar, days, true);
	}
	
	public static Calendar subtractDays(Calendar calendar, int days, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.DAY_OF_MONTH, days * -1);
		return clonedCalendar;
	}
	
	public static Calendar subtractHours(Calendar calendar, int hours)
	{
		return subtractHours(calendar, hours, true);
	}
	
	public static Calendar subtractHours(Calendar calendar, int hours, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.HOUR_OF_DAY, hours * -1);
		return clonedCalendar;
	}
	
	public static Calendar  subtractMinutes(Calendar calendar, int minutes)
	{
		return  subtractMinutes(calendar, minutes, true);
	}
	
	public static Calendar subtractMinutes(Calendar calendar, int minutes, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.MINUTE, minutes * -1);
		return clonedCalendar;
	}
	
	public static Calendar subtractSeconds(Calendar calendar, int seconds)
	{
		return subtractSeconds(calendar, seconds, true);
	}
	
	public static Calendar subtractSeconds(Calendar calendar, int seconds, boolean clone)
	{
		Calendar clonedCalendar = clone ? (Calendar) calendar.clone() : calendar;
		clonedCalendar.add(Calendar.SECOND, seconds * -1);
		return clonedCalendar;
	}
	
	public static String format()
	{
		return format(now());
	}
	
	public static String format(String pattern)
	{
		return format(now(), pattern);
	}
	
	public static String format(Calendar calendar)
	{
		return format(calendar, DEFAULT_FORMAT);
	}
	
	public static String formatTimezone(Calendar calendar)
	{
		return format(calendar, TIMEZONE_FORMAT);
	}
	
	public static String formatDisplay(Calendar calendar)
	{
		return format(calendar, DISPLAY_FORMAT);
	}
	
	public static String format(Calendar calendar, String pattern)
	{
		if(calendar != null && pattern != null)
		{
			return new SimpleDateFormat(pattern).format(calendar.getTime());
		}
		return null;
	}
	
	public static Calendar parse(String value)
	{
		return parse(value, DEFAULT_FORMAT);
	}
	
	public static Calendar parseDay(String value)
	{
		return parse(value, DAY_FORMAT);
	}
	
	public static Calendar parseMinute(String value)
	{
		return parse(value, MINUTE_FORMAT);
	}
	
	public static Calendar parse(String value, String pattern)
	{
		try
		{
			return get(new SimpleDateFormat(pattern).parse(value));
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static boolean isToday(Calendar calendar)
	{
		return isSameDay(calendar, today());
	}
	
	public static boolean isSameDay(Calendar calendar1, Calendar calendar2)
	{
		return (calendar1.get(Calendar.ERA) == calendar2.get(Calendar.ERA) &&
				calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
				calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR));
	}
	
	public static boolean isBusinessDay()
	{
		return !isWeekendOrHoliday(today());
	}
	
	public static Calendar nextBusinessDay()
	{
		return nextBusinessDay(today());
	}
	
	public static Calendar nextBusinessDay(Calendar day)
	{
		Calendar businessDay = day;
		while(isWeekendOrHoliday(businessDay))
		{
			businessDay = addDays(businessDay, 1);
		}
		return businessDay;
	}
	
	public static Calendar nextEffectiveDay()
	{
		return nextEffectiveDay(today());
	}
	
	public static Calendar nextEffectiveDay(Calendar day)
	{
		Calendar businessDay = nextBusinessDay(day);
		Calendar effectiveDay = addDays(businessDay, 1);;
		while(isWeekendOrHoliday(effectiveDay))
		{
			effectiveDay = addDays(effectiveDay, 1);
		}
		return effectiveDay;
	}
	
	public static boolean isWeekend()
	{
		return isWeekend(now());
	}
	
	public static boolean isWeekend(Calendar calendar)
	{
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return Calendar.SATURDAY == dayOfWeek || Calendar.SUNDAY == dayOfWeek;
	}
	
	public static boolean isHoliday()
	{
		return isHoliday(now());
	}
	
	public static boolean isHoliday(Calendar calendar)
	{
		return HOLIDAYS.contains(day(calendar));
	}
	
	public static boolean isWeekendOrHoliday()
	{
		return isWeekendOrHoliday(now());
	}
	
	public static boolean isWeekendOrHoliday(Calendar calendar)
	{
		return isWeekend(calendar) || isHoliday(calendar);
	}
	
	public static void main(String[] args)
	{
		System.out.println(format(parseDay(null)));
	}
	
}
