package roth.lib.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.lang.List;
import roth.lib.java.time.Time;

public class CronTabUtil
{
	protected static final String ALL = "*";
	protected static final String LAST_DAY = "L";
	protected static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
	protected static final Pattern STEP_PATTERN = Pattern.compile("/(\\d+)");
	
	protected static final int MINUTE_MIN 	= 0;
	protected static final int MINUTE_MAX 	= 59;
	protected static final int HOUR_MIN 	= 0;
	protected static final int HOUR_MAX 	= 23;
	protected static final int DAY_MIN 		= 1;
	protected static final int DAY_MAX 		= 31;
	protected static final int MONTH_MIN 	= 1;
	protected static final int MONTH_MAX 	= 12;
	protected static final int WEEKDAY_MIN 	= 0;
	protected static final int WEEKDAY_MAX 	= 6;
	
	
	protected CronTabUtil()
	{
		
	}
	
	public static boolean isNow(Time now, String minute, String hour, String day, String month, String weekday)
	{
		boolean matches = false;
		if(isAll(minute) || isMinuteNow(minute, now))
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
		return ALL.equals(StringUtil.trim(value));
	}
	
	protected static List<Integer> parse(String expression, int min, int max)
	{
		List<Integer> values = new List<>();
		Integer value = NumberUtil.parseInteger(expression);
		if(value != null)
		{
			values.add(value);
		}
		else
		{
			Integer start = null;
			Integer end = null;
			int step = 1;
			if(expression.contains(ALL))
			{
				start = min;
				end = max;
			}
			else
			{
				Matcher matcher = RANGE_PATTERN.matcher(expression);
				if(matcher.find())
				{
					start = Math.max(min, NumberUtil.parseInt(matcher.group(1)));
					end = Math.min(max, NumberUtil.parseInt(matcher.group(2)));
				}
			}
			if(start != null & end != null)
			{
				Matcher matcher = STEP_PATTERN.matcher(expression);
				if(matcher.find())
				{
					step = Math.max(step, NumberUtil.parseInt(matcher.group(1)));
				}
				for(int i = start; i <= end; i += step)
				{
					values.add(i);
				}
			}
		}
		return values;
	}
	
	protected static boolean matches(String value, int timeValue, int min, int max)
	{
		boolean matches = false;
		if(value != null)
		{
			for(String expression : StringUtil.trim(value).split(","))
			{
				matches = parse(expression, min, max).contains(timeValue);
			}
		}
		return matches;
	}
	
	public static boolean isMinuteNow(String value, Time now)
	{
		return matches(value, now.getMinute(), MINUTE_MIN, MINUTE_MAX);
	}
	
	protected static boolean isHourNow(String value, Time now)
	{
		return matches(value, now.getHour(), HOUR_MIN, HOUR_MAX);
	}
	
	protected static boolean isDayNow(String value, Time now)
	{
		return matches(value, now.getDay(), DAY_MIN, DAY_MAX);
	}
	
	public static boolean isMonthNow(String value, Time now)
	{
		return matches(value, now.getMonth(), HOUR_MIN, HOUR_MAX);
	}
	
	public static boolean isWeekdayNow(String value, Time now)
	{
		return matches(value, now.getWeekday(), WEEKDAY_MIN, WEEKDAY_MAX);
	}
	
	public static void main(String[] args)
	{
		System.out.println(isMinuteNow("*/5", new Time()));
	}
	
}
