package roth.lib.net.http.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class HeaderUtil
{
	public static TimeZone GMT = TimeZone.getTimeZone("GMT");
	public static SimpleDateFormat HEADER_DATE;
	
	static
	{
		HEADER_DATE = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		HEADER_DATE.setTimeZone(GMT);
	}
	
	private HeaderUtil()
	{
		
	}
	
	public static String formatCalendar(Calendar calendar)
	{
		return formatDate(calendar.getTime());
	}
	
	public static String formatDate(Date date)
	{
		return HEADER_DATE.format(date);
	}
	
	public static Calendar parseCalendar(String value)
	{
		Calendar calendar = null;
		Date date = parseDate(value);
		if(date != null)
		{
			calendar = new GregorianCalendar();
			calendar.setTime(date);
		}
		return calendar;
	}
	
	public static Date parseDate(String value)
	{
		Date date = null;
		try
		{
			date = HEADER_DATE.parse(value);
		}
		catch(Exception e)
		{
			
		}
		return date;
	}
	
}
