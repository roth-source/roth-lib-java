package roth.lib.java.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import roth.lib.java.util.EnumUtil;

@SuppressWarnings("serial")
public class List<E> extends LinkedList<E>
{
	protected boolean allowNull;
	
	public List()
	{
		super();
	}
	
	@SafeVarargs
	public List(E...c)
	{
		super();
		if(c != null)
		{
			addAll(Arrays.asList(c));
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> array(E...c)
	{
		if(c != null)
		{
			collection(Arrays.asList(c));
		}
		return this;
	}
	
	public List<E> collection(Collection<? extends E> c)
	{
		addAll(c);
		return this;
	}
	
	public boolean isAllowNull()
	{
		return allowNull;
	}
	
	public List<E> allowNull()
	{
		this.allowNull = true;
		return this;
	}
	
	public List<E> setAllowNull(boolean allowNull)
	{
		this.allowNull = allowNull;
		return this;
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
	public E getFirst()
	{
		return super.getFirst();
	}

	@Override
	public E getLast()
	{
		return super.getLast();
	}

	@Override
	public E removeFirst()
	{
		return super.removeFirst();
	}

	@Override
	public E removeLast()
	{
		return super.removeLast();
	}

	@Override
	public void addFirst(E e)
	{
		if(e != null || allowNull)
		{
			super.addFirst(e);
		}
	}

	@Override
	public void addLast(E e)
	{
		if(e != null || allowNull)
		{
			super.addLast(e);
		}
	}

	@Override
	public boolean contains(Object o)
	{
		return super.contains(o);
	}

	@Override
	public int size()
	{
		return super.size();
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
	public void clear()
	{
		super.clear();
	}

	@Override
	public E get(int index)
	{
		return super.get(index);
	}

	@Override
	public E set(int index, E element)
	{
		if(element != null || allowNull)
		{
			return super.set(index, element);
		}
		else
		{
			return null;
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
	public E peek()
	{
		return super.peek();
	}

	@Override
	public E element()
	{
		return super.element();
	}

	@Override
	public E poll()
	{
		return super.poll();
	}

	@Override
	public E remove()
	{
		return super.remove();
	}

	@Override
	public boolean offer(E e)
	{
		if(e != null || allowNull)
		{
			return super.offer(e);
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean offerFirst(E e)
	{
		if(e != null || allowNull)
		{
			return super.offerFirst(e);
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean offerLast(E e)
	{
		if(e != null || allowNull)
		{
			return super.offerLast(e);
		}
		else
		{
			return false;
		}
	}

	@Override
	public E peekFirst()
	{
		return super.peekFirst();
	}

	@Override
	public E peekLast()
	{
		return super.peekLast();
	}

	@Override
	public E pollFirst()
	{
		return super.pollFirst();
	}

	@Override
	public E pollLast()
	{
		return super.pollLast();
	}

	@Override
	public void push(E e)
	{
		if(e != null || allowNull)
		{
			super.push(e);
		}
	}

	@Override
	public E pop()
	{
		return super.pop();
	}

	@Override
	public boolean removeFirstOccurrence(Object o)
	{
		return super.removeFirstOccurrence(o);
	}

	@Override
	public boolean removeLastOccurrence(Object o)
	{
		return super.removeLastOccurrence(o);
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return super.listIterator(index);
	}

	@Override
	public Iterator<E> descendingIterator()
	{
		return super.descendingIterator();
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
	public ListIterator<E> listIterator()
	{
		return super.listIterator();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		return new List<E>().collection(super.subList(fromIndex, toIndex));
	}

	@Override
	public boolean equals(Object object)
	{
		return super.equals(object);
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(List<E> list)
	{
		boolean equals = false;
		if(list != null)
		{
			if(size() == list.size())
			{
				List<E> list1 = (List<E>) this.clone();
				List<E> list2 = (List<E>) list.clone();
				Iterator<E> iterator = list1.iterator();
				while(iterator.hasNext())
				{
					E element = iterator.next();
					int index = list2.indexOf(element);
					if(index > -1)
					{
						iterator.remove();
						list2.remove(index);
					}
					else
					{
						break;
					}
				}
				equals = list1.isEmpty() && list2.isEmpty();
			}
		}
		return equals;
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex)
	{
		super.removeRange(fromIndex, toIndex);
	}

	@Override
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
	
	@Override
	public boolean containsAll(Collection<?> c)
	{
		return super.containsAll(c);
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
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		String seperater = "";
		for(E element : this)
		{
			if(element != null || allowNull)
			{
				builder.append(seperater);
				builder.append(element != null ? element.toString() : "null");
				seperater = ",";
			}
		}
		return builder.toString();
	}
	
	public List<E> section(int index, int sections)
	{
		List<E> section = new List<E>();
		int sectionSize = (int) Math.ceil(((double) size() / (double) sections));
		int fromIndex = index * sectionSize;
		int toIndex = Math.min(((index + 1) * sectionSize), size());
		if(fromIndex < toIndex)
		{
			section = subList(fromIndex, toIndex);
		}
		return section;
	}
	
	@SafeVarargs
	public static <E> List<E> fromArray(E...array)
	{
		return new List<E>(array);
	}
	
	public static <T extends Enum<?>> List<T> fromString(String values, Class<T> klass)
	{
		return fromString(values, klass, ",");
	}
	
	public static <T extends Enum<?>> List<T> fromString(String values, Class<T> klass, String seperator)
	{
		List<T> list = new List<>();
		if(values != null)
		{
			for(String value : values.split(seperator))
			{
				list.add(EnumUtil.fromString(value, klass));
			}
		}
		return list;
	}
	
}
