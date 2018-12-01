package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.moppletop.aoc.AbstractDay;

public class Day201801 extends AbstractDay
{

	private List<Integer> _frequencies;

	public Day201801() throws IOException
	{
		super(2018, 1);
	}

	@Override
	protected void preSolve()
	{
		_frequencies = inputList().stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	@Override
	protected Object solvePart1()
	{
		int result = 0;

		for (int frequency : _frequencies)
		{
			result += frequency;
		}

		return result;
	}

	@Override
	protected Object solvePart2()
	{
		Set<Integer> seen = new HashSet<>();
		int result = 0;
		int i = 0;

		while (true)
		{
			result += _frequencies.get(i++ % _frequencies.size());

			if (!seen.add(result))
			{
				return result;
			}
		}
	}
}
