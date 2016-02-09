package roth.lib.java.db.config;

import java.io.Serializable;
import java.lang.reflect.Field;

import roth.lib.java.annotation.Entity;
import roth.lib.java.util.BaseUtil;

@Entity
@SuppressWarnings("serial")
public class IdConfig implements Serializable
{
	public static final int TABLE_LENGTH 	= 2;
	public static final int SERVER_LENGTH 	= 2;
	public static final int TIME_LENGTH 	= 8;
	public static final int RANDOM_LENGTH 	= 8;
	
	@LengthConfig(min = 1, max = Integer.BYTES)
	protected int tableEncodedLength = TABLE_LENGTH;
	
	@LengthConfig(min = 1, max = Integer.BYTES)
	protected int serverEncodedLength = SERVER_LENGTH;
	
	@LengthConfig(min = 7, max = Long.BYTES)
	protected int timeEncodedLength = TIME_LENGTH;
	
	@LengthConfig(min = 5, max = Long.BYTES)
	protected int randomEncodedLength = RANDOM_LENGTH;
	
	public IdConfig()
	{
		
	}
	
	public void validate()
	{
		for(Field field : getClass().getDeclaredFields())
		{
			LengthConfig length = field.getDeclaredAnnotation(LengthConfig.class);
			if(length != null)
			{
				try
				{
					int value = (int) field.get(this);
					if(value < length.min() || length.max() < value)
					{
						throw new DbConfigException(value + " " + field.getName() + " is invalid");
					}
				}
				catch(IllegalAccessException e)
				{
					
				}
			}
		}
	}
	
	public int getTableEncodedLength()
	{
		return tableEncodedLength;
	}
	
	public int getServerEncodedLength()
	{
		return serverEncodedLength;
	}
	
	public int getTimeEncodedLength()
	{
		return timeEncodedLength;
	}
	
	public int getRandomEncodedLength()
	{
		return randomEncodedLength;
	}
	
	public int getTotalEncodedLength()
	{
		return tableEncodedLength + serverEncodedLength + timeEncodedLength + randomEncodedLength;
	}
	
	protected long getMaxValue(int length)
	{
		return (long) Math.pow(BaseUtil.BASE_62, length);
	}
	
	public int getTableMaxValue()
	{
		return (int) getMaxValue(tableEncodedLength);
	}
	
	public int getServerMaxValue()
	{
		return (int) getMaxValue(serverEncodedLength);
	}
	
	public long getTimeMaxValue()
	{
		return getMaxValue(timeEncodedLength);
	}
	
	public long getRandomMaxValue()
	{
		return getMaxValue(randomEncodedLength);
	}
	
	public static void main(String[] args)
	{
		IdConfig config = new IdConfig();
		config.timeEncodedLength = 10;
		config.validate();
	}
	
}
