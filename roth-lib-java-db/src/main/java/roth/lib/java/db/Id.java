package roth.lib.java.db;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

import roth.lib.java.db.config.IdConfig;
import roth.lib.java.util.BaseUtil;
import roth.lib.java.util.StringUtil;

public class Id
{
	protected static IdConfig config = new IdConfig();
	
	protected int table;
	protected int server;
	protected long time;
	protected long random;
	
	protected Id()
	{
		
	}
	
	public Id(int table, int server)
	{
		setTable(table);
		setServer(server);
		setTime(System.currentTimeMillis());
		setRandom(generateRandom());
	}
	
	public static void setConfig(IdConfig idConfig)
	{
		config = idConfig;
	}
	
	public static long generateRandom()
	{
		return (long) (new Random().nextDouble() * config.getRandomMax());
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
		this.table = table;
		return this;
	}
	
	public Id setServer(int server)
	{
		this.server = server;
		return this;
	}
	
	public Id setTime(long time)
	{
		this.time = time;
		return this;
	}
	
	public Id setRandom(long random)
	{
		this.random = random;
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(StringUtil.padLeftLimit(BaseUtil.encodeBase62(table), 	config.getTableLength(), 	BaseUtil.BASE_PAD));
		builder.append(StringUtil.padLeftLimit(BaseUtil.encodeBase62(server), 	config.getServerLength(), 	BaseUtil.BASE_PAD));
		builder.append(StringUtil.padLeftLimit(BaseUtil.encodeBase62(time), 	config.getTimeLength(), 	BaseUtil.BASE_PAD));
		builder.append(StringUtil.padLeftLimit(BaseUtil.encodeBase62(random), 	config.getRandomLength(), 	BaseUtil.BASE_PAD));
		return builder.toString();
	}
	
	public byte[] toBytes()
	{
		ByteBuffer buffer = ByteBuffer.allocate(config.getTotalLength());
		buffer.put(Arrays.copyOfRange(ByteBuffer.allocate(Integer.BYTES)	.putInt(table).array(), 	Integer.BYTES 	- config.getTableLength(), 		Integer.BYTES));
		buffer.put(Arrays.copyOfRange(ByteBuffer.allocate(Integer.BYTES)	.putInt(server).array(), 	Integer.BYTES 	- config.getServerLength(), 	Integer.BYTES));
		buffer.put(Arrays.copyOfRange(ByteBuffer.allocate(Long.BYTES)		.putLong(time).array(), 	Long.BYTES 		- config.getTimeLength(), 		Long.BYTES));
		buffer.put(Arrays.copyOfRange(ByteBuffer.allocate(Long.BYTES)		.putLong(random).array(), 	Long.BYTES 		- config.getRandomLength(), 	Long.BYTES));
		return buffer.array();
	}
	
	public static Id fromString(String value)
	{
		Id id = new Id();
		int position = 0;
		id.setTable(	BaseUtil.decodeBase62(value.substring(position, 	(position += config.getTableLength())))		.intValue());
		id.setServer(	BaseUtil.decodeBase62(value.substring(position, 	(position += config.getServerLength())))	.intValue());
		id.setTime(		BaseUtil.decodeBase62(value.substring(position, 	(position += config.getTimeLength())))		.longValue());
		id.setRandom(	BaseUtil.decodeBase62(value.substring(position, 	(position += config.getRandomLength())))	.longValue());
		return id;
	}
	
	public static Id fromBytes(byte[] bytes)
	{
		Id id = new Id();
		int position = 0;
		id.setTable(	((ByteBuffer) ByteBuffer.allocate(Integer.BYTES)	.position(Integer.BYTES 	- config.getTableLength()))		.put(Arrays.copyOfRange(bytes, position, (position += config.getTableLength())))	.getInt(0));
		id.setServer(	((ByteBuffer) ByteBuffer.allocate(Integer.BYTES)	.position(Integer.BYTES 	- config.getServerLength()))	.put(Arrays.copyOfRange(bytes, position, (position += config.getServerLength())))	.getInt(0));
		id.setTime(		((ByteBuffer) ByteBuffer.allocate(Long.BYTES)		.position(Long.BYTES 		- config.getTimeLength()))		.put(Arrays.copyOfRange(bytes, position, (position += config.getTimeLength())))		.getLong(0));
		id.setRandom(	((ByteBuffer) ByteBuffer.allocate(Long.BYTES)		.position(Long.BYTES 		- config.getRandomLength()))	.put(Arrays.copyOfRange(bytes, position, (position += config.getRandomLength())))	.getLong(0));
		return id;
	}
	
	public static void main(String[] args)
	{
		{
			Id id = new Id(10, 1);
			String value = id.toString();
			System.out.println(value);
			
			Id id2 = Id.fromString(value);
			String value2 = id2.toString();
			System.out.println(value2);
		}
		{
			Id id = new Id(10, 1);
			byte[] bytes = id.toBytes();
			System.out.println(Arrays.toString(bytes));
			
			Id id2 = Id.fromBytes(bytes);
			byte[] bytes2 = id2.toBytes();
			System.out.println(Arrays.toString(bytes2));
		}
	}
	
}
