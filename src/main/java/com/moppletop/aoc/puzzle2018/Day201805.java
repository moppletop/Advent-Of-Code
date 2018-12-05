package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashSet;
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
		StringBuilder chain = getPolymer();
		int smallest = Integer.MAX_VALUE;
		char[] chainChars = new char[chain.length()];
		chain.getChars(0, chain.length(), chainChars, 0);

		long time = System.currentTimeMillis();

		for (char ch : chainChars)
		{
			char lowerCh = Character.toLowerCase(ch);

			if (!testUnits.add(lowerCh))
			{
				continue;
			}

			StringBuilder innerChain = getPolymer();

			for (int i = 0; i < innerChain.length(); i++)
			{
				if (lowerCh == Character.toLowerCase(innerChain.charAt(i)))
				{
					innerChain.deleteCharAt(i);
				}
			}

			int chainLength = reactPolymer(innerChain);

			if (chainLength < smallest)
			{
				smallest = chainLength;
			}
		}

		System.out.println(System.currentTimeMillis() - time);

		return smallest;
	}

	private StringBuilder getPolymer()
	{
		return new StringBuilder(inputFirstLine());
	}

	private int reactPolymer(StringBuilder chain)
	{
		boolean hasReaction = true;

		while (hasReaction)
		{
			hasReaction = false;

			for (int i = 0; i < chain.length() - 1; i++)
			{
				char a = chain.charAt(i), b = chain.charAt(i + 1);

				if (a != b && Character.toLowerCase(a) == Character.toLowerCase(b))
				{
					chain.delete(i, i + 2);
					hasReaction = true;
					break;
				}
			}
		}

		return chain.length();
	}
}
