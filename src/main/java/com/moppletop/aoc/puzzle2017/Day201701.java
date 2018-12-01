package com.moppletop.aoc.puzzle2017;

import java.io.IOException;
import java.util.List;

import com.moppletop.aoc.AbstractDay;
import com.moppletop.aoc.util.CircularList;

public class Day201701 extends AbstractDay
{

	private List<Integer> _digits;

	public Day201701() throws IOException
	{
		super(2017, 1);
	}

	@Override
	protected void preSolve()
	{
		String input = inputFirstLine();
		_digits = new CircularList<>(input.length());

		for (char c : input.toCharArray())
		{
			_digits.add(Integer.parseInt(String.valueOf(c)));
		}
	}

	@Override
	protected Object solvePart1()
	{
		return getTotal(1);
	}

	@Override
	protected Object solvePart2()
	{
		return getTotal(_digits.size() / 2);
	}

	private int getTotal(int jump)
	{
		int total = 0;

		for (int i = 0; i < _digits.size(); i++)
		{
			int value = _digits.get(i);

			if (_digits.get(i + jump) == value)
			{
				total += value;
			}
		}

		return total;
	}
}
