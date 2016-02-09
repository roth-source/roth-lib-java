package roth.lib.java.db;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

import roth.lib.java.db.config.IdConfig;
import roth.lib.java.db.util.ByteUtil;
import roth.lib.java.util.BaseUtil;

public class Id
{
	protected IdConfig config;
	protected int table;
	protected int server;
	protected long time;
	protected long random;
	
	public Id(IdConfig config)
	{
		this.config = config;
	}
	
	public Id(IdConfig config, int table, int server)
	{
		this(config);
		setTable(table);
		setServer(server);
		setTime(System.currentTimeMillis());
		setRandom(generateRandom());
	}
	
	public long generateRandom()
	{
		return (long) (new Random().nextDouble() * config.getRandomMaxValue());
	}
	
	public int getTable()
	{
		return table;
	}
	
	public int getServer()
	{
		return server;
	}
	
	public long getTime()
	{
		return time;
	}
	
	public long getRandom()
	{
		return random;
	}
	
	public Id setTable(int table)
	{
		if(table < config.getTableMaxValue())
		{
			this.table = table;
			return this;
		}
		else
		{
			throw new DbException(table + " table is larger than " + config.getTableMaxValue());
		}
	}
	
	public Id setServer(int server)
	{
		if(server < config.getServerMaxValue())
		{
			this.server = server;
			return this;
		}
		else
		{
			throw new DbException(server + " server is larger than " + config.getServerMaxValue());
		}
	}
	
	public Id setTime(long time)
	{
		if(time < config.getTimeMaxValue())
		{
			this.time = time;
			return this;
		}
		else
		{
			throw new DbException(time + " time is larger than " + config.getTimeMaxValue());
		}
	}
	
	public Id setRandom(long random)
	{
		if(random < config.getRandomMaxValue())
		{
			this.random = random;
			return this;
		}
		else
		{
			throw new DbException(random + " random is larger than " + config.getRandomMaxValue());
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(BaseUtil.encodeBase62(table, config.getTableEncodedLength()));
		builder.append(BaseUtil.encodeBase62(server, config.getServerEncodedLength()));
		builder.append(BaseUtil.encodeBase62(time, config.getTimeEncodedLength()));
		builder.append(BaseUtil.encodeBase62(random, config.getRandomEncodedLength()));
		return builder.toString();
	}
	
	public byte[] toBytes()
	{
		ByteBuffer buffer = ByteBuffer.allocate(config.getTotalEncodedLength());
		buffer.put(ByteUtil.toBytes(table, config.getTableEncodedLength()));
		buffer.put(ByteUtil.toBytes(server, config.getServerEncodedLength()));
		buffer.put(ByteUtil.toBytes(time, config.getTimeEncodedLength()));
		buffer.put(ByteUtil.toBytes(random, config.getRandomEncodedLength()));
		return buffer.array();
	}
	
	public static Id fromString(IdConfig config, String value)
	{
		Id id = new Id(config);
		int position = 0;
		id.setTable(BaseUtil.decodeBase62(value.substring(position, (position += config.getTableEncodedLength()))).intValue());
		id.setServer(BaseUtil.decodeBase62(value.substring(position, (position += config.getServerEncodedLength()))).intValue());
		id.setTime(BaseUtil.decodeBase62(value.substring(position, (position += config.getTimeEncodedLength()))).longValue());
		id.setRandom(BaseUtil.decodeBase62(value.substring(position, (position += config.getRandomEncodedLength()))).longValue());
		return id;
	}
	
	public static Id fromBytes(IdConfig config, byte[] bytes)
	{
		Id id = new Id(config);
		int position = 0;
		id.setTable(ByteUtil.toInt(Arrays.copyOfRange(bytes, position, (position += config.getTableEncodedLength()))));
		id.setServer(ByteUtil.toInt(Arrays.copyOfRange(bytes, position, (position += config.getServerEncodedLength()))));
		id.setTime(ByteUtil.toLong(Arrays.copyOfRange(bytes, position, (position += config.getTimeEncodedLength()))));
		id.setRandom(ByteUtil.toLong(Arrays.copyOfRange(bytes, position, (position += config.getRandomEncodedLength()))));
		return id;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (random ^ (random >>> 32));
		result = prime * result + server;
		result = prime * result + table;
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(this == object)
		{
			return true;
		}
		if(object == null)
		{
			return false;
		}
		if(!(object instanceof Id))
		{
			return false;
		}
		Id other = (Id) object;
		if(random != other.random)
		{
			return false;
		}
		if(server != other.server)
		{
			return false;
		}
		if(table != other.table)
		{
			return false;
		}
		if(time != other.time)
		{
			return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		IdConfig config = new IdConfig();
		{
			Id id = new Id(config, 10, 1);
			String value = id.toString();
			System.out.println(value);
			
			Id id2 = Id.fromString(config, value);
			String value2 = id2.toString();
			System.out.println(value2);
		}
		{
			Id id = new Id(config, 10, 1);
			byte[] bytes = id.toBytes();
			System.out.println(Arrays.toString(bytes));
			
			Id id2 = Id.fromBytes(config, bytes);
			byte[] bytes2 = id2.toBytes();
			System.out.println(Arrays.toString(bytes2));
		}
	}
	
}
