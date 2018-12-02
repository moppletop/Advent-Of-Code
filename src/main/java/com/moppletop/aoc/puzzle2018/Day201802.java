package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.moppletop.aoc.AbstractDay;

public class Day201802 extends AbstractDay
{

	public Day201802() throws IOException
	{
		super(2018, 2);
	}

	@Override
	protected void preSolve()
	{

	}

	@Override
	protected Object solvePart1()
	{
		int twos = 0, threes = 0;

		for (String id : inputList())
		{
			Map<Character, Integer> timesSeen = new HashMap<>();

			for (char ch : id.toCharArray())
			{
				timesSeen.put(ch, timesSeen.getOrDefault(ch, 0) + 1);
			}

			boolean hasTwo = false, hasThree = false;

			for (int value : timesSeen.values())
			{
				if (value == 2)
				{
					hasTwo = true;
				}
				else if (value == 3)
				{
					hasThree = true;
				}
			}

			if (hasTwo)
			{
				twos++;
			}
			if (hasThree)
			{
				threes++;
			}
		}

		return twos * threes;
	}

	@Override
	protected Object solvePart2()
	{
		for (String idA : inputList())
		{
			idLoop: for (String idB : inputList())
			{
				if (idA.equals(idB))
				{
					continue;
				}

				char[] charsA = idA.toCharArray(), charsB = idB.toCharArray();
				int difference = -1;
				int differences = 0;

				for (int i = 0; i < charsA.length; i++)
				{
					if (charsA[i] != charsB[i])
					{
						if (differences > 0)
						{
							continue idLoop;
						}

						difference = i;
						differences++;
					}
				}

				if (difference == -1)
				{
					continue;
				}

				return idA.substring(0, difference) + idA.substring(difference + 1);
			}
		}

		return null;
	}
}
