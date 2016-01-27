package roth.lib.java.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@SuppressWarnings("serial")
public class Array<E> extends ArrayList<E>
{
	protected boolean allowNull;
	
	public Array()
	{
		super();
	}
	
	public Array(boolean allowNull)
	{
		super();
		this.allowNull = allowNull;
	}
	
	public Array(Collection<? extends E> c)
	{
		super(c);
	}
	
	@SafeVarargs
	public Array(boolean allowNull, E...c)
	{
		this(allowNull, Arrays.asList(c));
	}
	
	public Array(boolean allowNull, Collection<? extends E> c)
	{
		super(c);
		this.allowNull = allowNull;
	}
	
	public Array(int initialCapacity)
	{
		super(initialCapacity);
	}
	
	public boolean isAllowNull()
	{
		return allowNull;
	}
	
	public Array<E> setAllowNull(boolean allowNull)
	{
		this.allowNull = allowNull;
		return this;
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
	public void trimToSize()
	{
		super.trimToSize();
	}

	@Override
	public void ensureCapacity(int minCapacity)
	{
		super.ensureCapacity(minCapacity);
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
	public int indexOf(Object o)
	{
		return super.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return super.lastIndexOf(o);
	}

	@Override
	public Object clone()
	{
		return super.clone();
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
	public E get(int index)
	{
		return super.get(index);
	}

	@Override
	public E set(int index, E element)
	{
		return super.set(index, element);
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
	public void add(int index, E element)
	{
		if(element != null || allowNull)
		{
			super.add(index, element);
		}
	}

	@Override
	public E remove(int index)
	{
		return super.remove(index);
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
	public boolean addAll(int index, Collection<? extends E> c)
	{
		boolean added = false;
		for(E e : c)
		{
			if(e != null || allowNull)
			{
				add(index++, e);
				added = true;
			}
		}
		return added;
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex)
	{
		super.removeRange(fromIndex, toIndex);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return super.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return super.retainAll(c);
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return super.listIterator(index);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return super.listIterator();
	}

	@Override
	public Iterator<E> iterator()
	{
		return super.iterator();
	}

	@Override
	public Array<E> subList(int fromIndex, int toIndex)
	{
		return new Array<E>(super.subList(fromIndex, toIndex));
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
	public boolean removeIf(Predicate<? super E> filter)
	{
		return super.removeIf(filter);
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator)
	{
		super.replaceAll(operator);
	}

	@Override
	public void sort(Comparator<? super E> c)
	{
		super.sort(c);
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
	public boolean containsAll(Collection<?> c)
	{
		return super.containsAll(c);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
	
	@SafeVarargs
	public static <E> Array<E> fromArray(E...array)
	{
		return new Array<E>(Arrays.asList(array));
	}
	
}
