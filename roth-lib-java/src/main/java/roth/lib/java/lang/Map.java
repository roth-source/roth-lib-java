package roth.lib.java.lang;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("serial")
public class Map<K, V> extends LinkedHashMap<K, V>
{
	
	public Map()
	{
		super();
	}
	
	public Map(java.util.Map<? extends K, ? extends V> m)
	{
		super();
		putAll(m);
	}
	
	@Override
	public boolean containsValue(Object value)
	{
		return super.containsValue(value);
	}
	
	public K key(int index)
	{
		return new List<K>().collection(super.keySet()).get(index);
	}
	
	@Override
	public V get(Object key)
	{
		return super.get(key);
	}

	@Override
	public V getOrDefault(Object key, V defaultValue)
	{
		return super.getOrDefault(key, defaultValue);
	}

	@Override
	public void clear()
	{
		super.clear();
	}

	@Override
	protected boolean removeEldestEntry(Entry<K, V> eldest)
	{
		return super.removeEldestEntry(eldest);
	}

	@Override
	public Set<K> keySet()
	{
		return new Set<K>().collection(super.keySet());
	}

	@Override
	public Collection<V> values()
	{
		return super.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet()
	{
		return new Set<Entry<K, V>>().collection(super.entrySet());
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action)
	{
		super.forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
	{
		super.replaceAll(function);
	}

	@Override
	public int size()
	{
		return super.size();
	}

	@Override
	public boolean isEmpty()
	{
		return super.isEmpty();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return super.containsKey(key);
	}

	@Override
	public V put(K key, V value)
	{
		if(key != null)
		{
			return super.put(key, value);
		}
		else
		{
			return null;
		}
	}

	@Override
	public void putAll(java.util.Map<? extends K, ? extends V> m)
	{
		for(Entry<? extends K, ? extends V> e : m.entrySet())
		{
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public V remove(Object key)
	{
		return super.remove(key);
	}

	@Override
	public V putIfAbsent(K key, V value)
	{
		return super.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, Object value)
	{
		return super.remove(key, value);
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue)
	{
		return super.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(K key, V value)
	{
		return super.replace(key, value);
	}

	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
	{
		return super.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
	{
		return super.computeIfPresent(key, remappingFunction);
	}

	@Override
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
	{
		return super.compute(key, remappingFunction);
	}

	@Override
	public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
	{
		return super.merge(key, value, remappingFunction);
	}

	@Override
	public Object clone()
	{
		return super.clone();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
	
}
