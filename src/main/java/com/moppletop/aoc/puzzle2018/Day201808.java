package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.moppletop.aoc.AbstractDay;

public class Day201808 extends AbstractDay
{

	private List<Integer> _tree;

	public Day201808() throws IOException
	{
		super(2018, 8);
	}

	@Override
	protected void preSolve()
	{
		_tree = Arrays.stream(inputFirstLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	@Override
	protected Object solvePart1()
	{
		return sum(new LinkedList<>(_tree));
	}

	@Override
	protected Object solvePart2()
	{
		return valueOfRoot(new LinkedList<>(_tree));
	}

	private int sum(List<Integer> tree)
	{
		if (tree.isEmpty())
		{
			return 0;
		}

		int sum = 0;

		int children = tree.remove(0);
		int metadataEntries = tree.remove(0);

		for (int i = 0; i < children; i++)
		{
			sum += sum(tree);
		}

		for (int i = 0; i < metadataEntries; i++)
		{
			sum += tree.remove(0);
		}

		return sum;
	}

	private int valueOfRoot(List<Integer> tree)
	{
		if (tree.isEmpty())
		{
			return 0;
		}

		int value = 0;

		int children = tree.remove(0);
		int metadataEntries = tree.remove(0);
		int[] childrenValues = new int[children];

		for (int i = 0; i < children; i++)
		{
			childrenValues[i] = valueOfRoot(tree);
		}

		boolean hasChildren = children > 0;

		for (int i = 0; i < metadataEntries; i++)
		{
			int metadataValue = tree.remove(0);

			if (hasChildren)
			{
				if (metadataValue > 0 && metadataValue <= childrenValues.length)
				{
					value += childrenValues[metadataValue - 1];
				}
			}
			else
			{
				value += metadataValue;
			}
		}

		return value;
	}
}
