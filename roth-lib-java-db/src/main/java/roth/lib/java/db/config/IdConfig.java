package roth.lib.java.db.config;

import java.io.Serializable;
import java.lang.reflect.Field;

import roth.lib.java.util.BaseUtil;

@SuppressWarnings("serial")
public class IdConfig implements Serializable
{
	public static final int TABLE_LENGTH 	= 2;
	public static final int SERVER_LENGTH 	= 2;
	public static final int TIME_LENGTH 	= 8;
	public static final int RANDOM_LENGTH 	= 8;
	
	@Length(min = 1, max = Integer.BYTES)
	protected int tableLength = TABLE_LENGTH;
	
	@Length(min = 1, max = Integer.BYTES)
	protected int serverLength = SERVER_LENGTH;
	
	@Length(min = 7, max = Long.BYTES)
	protected int timeLength = TIME_LENGTH;
	
	@Length(min = 5, max = Long.BYTES)
	protected int randomLength = RANDOM_LENGTH;
	
	public IdConfig()
	{
		
	}
	
	public void validate()
	{
		for(Field field : getClass().getDeclaredFields())
		{
			Length length = field.getDeclaredAnnotation(Length.class);
			if(length != null)
			{
				try
				{
					int value = (int) field.get(this);
					if(value < length.min() || length.max() < value)
					{
						throw new ConfigException(value + " " + field.getName() + " is invalid");
					}
				}
				catch(IllegalAccessException e)
				{
					
				}
			}
		}
	}
	
	public int getTableLength()
	{
		return tableLength;
	}
	
	public int getServerLength()
	{
		return serverLength;
	}
	
	public int getTimeLength()
	{
		return timeLength;
	}
	
	public int getRandomLength()
	{
		return randomLength;
	}
	
	public int getTotalLength()
	{
		return tableLength + serverLength + timeLength + randomLength;
	}
	
	protected long getMax(int length)
	{
		return (long) Math.pow(BaseUtil.BASE_62, length);
	}
	
	public long getTableMax()
	{
		return getMax(tableLength);
	}
	
	public long getServerMax()
	{
		return getMax(serverLength);
	}
	
	public long getTimeMax()
	{
		return getMax(timeLength);
	}
	
	public long getRandomMax()
	{
		return getMax(randomLength);
	}
	
	public static void main(String[] args)
	{
		IdConfig config = new IdConfig();
		config.timeLength = 10;
		config.validate();
	}
	
}
