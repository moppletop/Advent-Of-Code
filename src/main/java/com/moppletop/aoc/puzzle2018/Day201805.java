package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.moppletop.aoc.AbstractDay;

public class Day201805 extends AbstractDay
{

	public Day201805() throws IOException
	{
		super(2018, 5);
	}

	@Override
	protected void preSolve()
	{

	}

	@Override
	protected Object solvePart1()
	{
		return reactPolymer(getPolymer());
	}

	@Override
	protected Object solvePart2()
	{
		Set<Character> testUnits = new HashSet<>();
		List<Character> chain = getPolymer();
		int smallest = Integer.MAX_VALUE;

		for (char ch : chain)
		{
			char lowerCh = Character.toLowerCase(ch);

			if (!testUnits.add(lowerCh))
			{
				continue;
			}

			List<Character> innerChain = getPolymer();
			innerChain.removeIf(element -> lowerCh == Character.toLowerCase(element));

			int chainLength = reactPolymer(innerChain);

			if (chainLength < smallest)
			{
				smallest = chainLength;
			}
		}

		return smallest;
	}

	private List<Character> getPolymer()
	{
		String polymer = inputFirstLine();
		List<Character> chain = new ArrayList<>();

		for (char ch : polymer.toCharArray())
		{
			chain.add(ch);
		}

		return chain;
	}

	private int reactPolymer(List<Character> chain)
	{
		boolean hasReaction = true;

		while (hasReaction)
		{
			hasReaction = false;

			for (int i = 0; i < chain.size() - 1; i++)
			{
				char a = chain.get(i), b = chain.get(i + 1);

				if (a != b && Character.toLowerCase(a) == Character.toLowerCase(b))
				{
					chain.remove(i);
					chain.remove(i);
					hasReaction = true;
					break;
				}
			}
		}

		return chain.size();
	}
}
