package roth.lib.java.code.java.unused;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class ValidLinkedList<T> extends LinkedList<T>
{
	
	public ValidLinkedList()
	{
		super();
	}
	
	public ValidLinkedList(Collection<? extends T> collection)
	{
		super();
		addAll(collection);
	}

	@Override
	public void addFirst(T element)
	{
		if(element != null)
		{
			super.addFirst(element);
		}
	}

	@Override
	public void addLast(T element)
	{
		if(element != null)
		{
			super.addLast(element);
		}
	}

	@Override
	public boolean add(T element)
	{
		return element != null ? super.add(element) : false;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection)
	{
		LinkedList<T> validList = new LinkedList<T>();
		for(T element : collection)
		{
			if(element != null)
			{
				validList.add(element);
			}
		}
		return super.addAll(validList);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection)
	{
		LinkedList<T> validList = new LinkedList<T>();
		for(T element : collection)
		{
			if(element != null)
			{
				validList.add(element);
			}
		}
		return super.addAll(index, validList);
	}

	@Override
	public void add(int index, T element)
	{
		if(element != null)
		{
			super.add(index, element);
		}
	}
	
}
