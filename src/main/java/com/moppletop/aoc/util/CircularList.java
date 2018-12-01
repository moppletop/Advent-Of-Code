package com.moppletop.aoc.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CircularList<E> extends ArrayList<E>
{

	public CircularList()
	{
	}

	public CircularList(int initialCapacity)
	{
		super(initialCapacity);
	}

	public CircularList(Collection<? extends E> c)
	{
		super(c);
	}

	@Override
	public E get(int index)
	{
		index %= size();

		return super.get(index);
	}

	@Override
	public void add(int index, E element)
	{
		index %= size();

		super.add(index, element);
	}

	@Override
	public E set(int index, E element)
	{
		index %= size();

		return super.set(index, element);
	}

	@Override
	public List<E> subList(int fromIndex, int elements)
	{
		int endIndex = fromIndex + elements;

		if (endIndex < size())
		{
			return super.subList(fromIndex, endIndex);
		}
		else
		{
			List<E> list = new ArrayList<>(elements);
			int wrappedIndex = endIndex % size();

			list.addAll(super.subList(fromIndex, size()));

			for (int i = 0; i < wrappedIndex; i++)
			{
				list.add(super.get(i));
			}

			return list;
		}
	}

	public void merge(int index, List<E> toMerge)
	{
		for (int i = 0; i < toMerge.size(); i++)
		{
			set(index + i, toMerge.get(i));
		}
	}
}
