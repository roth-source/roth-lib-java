package roth.lib.java.util;

import java.util.Calendar;

public class CronTabUtil
{
	protected static final String ALL = "*";
	protected static final String LAST_DAY = "L";
	
	protected CronTabUtil()
	{
		
	}
	
	public static boolean isNow(Calendar now, String min, String hour, String day, String month, String weekday)
	{
		boolean matches = false;
		if(isAll(min) || isMinNow(min, now))
		{
			if(isAll(hour) || isHourNow(hour, now))
			{
				if(isAll(day) || isDayNow(day, now))
				{
					if(isAll(month) || isMonthNow(month, now))
					{
						if(isAll(weekday) || isWeekdayNow(weekday, now))
						{
							matches = true;
						}
					}
				}
			}
		}
		return matches;
	}
	
	public static boolean isAll(String value)
	{
		return ALL.equals(value);
	}
	
	public static boolean isMinNow(String value, Calendar now)
	{
		boolean matches = false;
		int minNow = now.get(Calendar.MINUTE);
		String[] minValues = value.split(",");
		for(String minValue : minValues)
		{
			minValue = minValue.trim();
			Integer min = NumberUtil.parseInteger(minValue);
			if(min.equals(minNow))
			{
				matches = true;
				break;
			}
		}
		return matches;
	}
	
	protected static boolean isHourNow(String value, Calendar now)
	{
		boolean matches = false;
		int hourNow = now.get(Calendar.HOUR_OF_DAY);
		String[] hourValues = value.split(",");
		for(String hourValue : hourValues)
		{
			hourValue = hourValue.trim();
			Integer hour = NumberUtil.parseInteger(hourValue);
			if(hour.equals(hourNow))
			{
				matches = true;
				break;
			}
		}
		return matches;
	}
	
	protected static boolean isDayNow(String value, Calendar now)
	{
		boolean matches = false;
		int dayNow = now.get(Calendar.DAY_OF_MONTH);
		String[] dayValues = value.split(",");
		for(String dayValue : dayValues)
		{
			dayValue = dayValue.trim();
			if(LAST_DAY.equals(dayValue))
			{
				matches = true;
			}
			else
			{
				Integer day = NumberUtil.parseInteger(dayValue);
				if(day.equals(dayNow))
				{
					matches = true;
					break;
				}
			}
		}
		return matches;
	}
	
	public static boolean isMonthNow(String value, Calendar now)
	{
		boolean matches = false;
		int monthNow = now.get(Calendar.MONTH) + 1;
		String[] monthValues = value.split(",");
		for(String monthValue : monthValues)
		{
			monthValue = monthValue.trim();
			Integer month = NumberUtil.parseInteger(monthValue);
			if(month.equals(monthNow))
			{
				matches = true;
				break;
			}
		}
		return matches;
	}
	
	public static boolean isWeekdayNow(String value, Calendar now)
	{
		boolean matches = false;
		int weekdayNow = now.get(Calendar.DAY_OF_WEEK) - 1;
		String[] weekdayValues = value.split(",");
		for(String weekdayValue : weekdayValues)
		{
			weekdayValue = weekdayValue.trim();
			Integer weekday = NumberUtil.parseInteger(weekdayValue);
			if(weekday.equals(weekdayNow))
			{
				matches = true;
				break;
			}
		}
		return matches;
	}
	
}
