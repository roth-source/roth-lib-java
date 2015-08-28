package roth.lib.db;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class DbHashMap<V> extends LinkedHashMap<String, V>
{
	
	public DbHashMap()
	{
		super();
	}
	
	public DbHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
	{
		super(initialCapacity, loadFactor, accessOrder);
	}
	
	public DbHashMap(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}
	
	public DbHashMap(int initialCapacity)
	{
		super(initialCapacity);
	}
	
	public DbHashMap(Map<String, ? extends V> m)
	{
		putAll(m);
	}
	
	@Override
	public V get(Object key)
	{
		return key != null ? super.get(key.toString().toUpperCase()) : null;
	}
	
	@Override
	public V put(String key, V value)
	{
		return super.put(key.toUpperCase(), value);
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends V> m)
	{
		for(Map.Entry<? extends String, ? extends V> entry : m.entrySet())
		{
			put(entry.getKey(), entry.getValue());
		}
	}
	
}
