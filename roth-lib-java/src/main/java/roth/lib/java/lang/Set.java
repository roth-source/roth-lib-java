package roth.lib.java.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class Set<E> extends LinkedHashSet<E>
{
	protected boolean allowNull;
	
	public Set()
	{
		super();
	}
	
	@SafeVarargs
	public Set(E...c)
	{
		super();
		addAll(Arrays.asList(c));
	}
	
	public Set<E> load(Collection<E> c)
	{
		Set<E> set = new Set<E>();
		set.addAll(c);
		return set;
	}
	
	public boolean isAllowNull()
	{
		return allowNull;
	}
	
	public Set<E> allowNull()
	{
		this.allowNull = true;
		return this;
	}
	
	public Set<E> setAllowNull(boolean allowNull)
	{
		this.allowNull = allowNull;
		return this;
	}
	
	@Override
	public boolean removeIf(Predicate<? super E> filter)
	{
		return super.removeIf(filter);
	}

	@Override
	public Stream<E> stream()
	{
		return super.stream();
	}

	@Override
	public Stream<E> parallelStream()
	{
		return super.parallelStream();
	}

	@Override
	public void forEach(Consumer<? super E> action)
	{
		super.forEach(action);
	}

	@Override
	public Spliterator<E> spliterator()
	{
		return super.spliterator();
	}

	@Override
	public Iterator<E> iterator()
	{
		return super.iterator();
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
	public boolean contains(Object o)
	{
		return super.contains(o);
	}

	@Override
	public boolean add(E e)
	{
		if(e != null || allowNull)
		{
			return super.add(e);
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean remove(Object o)
	{
		return super.remove(o);
	}

	@Override
	public void clear()
	{
		super.clear();
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
	public boolean removeAll(Collection<?> c)
	{
		return super.removeAll(c);
	}

	@Override
	public Object[] toArray()
	{
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return super.toArray(a);
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray(Class<E> klass)
	{
		return super.toArray((E[]) java.lang.reflect.Array.newInstance(klass, size()));
	}
	
	@Override
	public boolean containsAll(Collection<?> c)
	{
		return super.containsAll(c);
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(E...c)
	{
		return addAll(Arrays.asList(c));
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		boolean added = false;
		for(E e : c)
		{
			if(add(e))
			{
				added = true;
			}
		}
		return added;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return super.retainAll(c);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
	
	public static <E> Set<E> fromArray(E[] array)
	{
		return new Set<E>(array);
	}
	
}
