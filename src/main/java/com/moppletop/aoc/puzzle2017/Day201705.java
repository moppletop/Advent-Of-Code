package com.moppletop.aoc.puzzle2017;

import java.io.IOException;

import com.moppletop.aoc.AbstractDay;

public class Day201705 extends AbstractDay
{

	public Day201705() throws IOException
	{
		super(2017, 5);
	}

	@Override
	protected void preSolve()
	{

	}

	@Override
	protected Object solvePart1()
	{
		int[] commands = inputList().stream()
				.mapToInt(Integer::parseInt)
				.toArray();
		int index = 0, jumps = 0;

		while (true)
		{
			int command = commands[index];

			commands[index] = command + 1;
			index += command;
			jumps++;

			if (index < 0 || index >= commands.length)
			{
				return jumps;
			}
		}
	}

	@Override
	protected Object solvePart2()
	{
		int[] commands = inputList().stream()
				.mapToInt(Integer::parseInt)
				.toArray();
		int index = 0, jumps = 0;

		while (true)
		{
			int command = commands[index];

			commands[index] = command >= 3 ? command - 1 : command + 1;
			index += command;
			jumps++;

			if (index < 0 || index >= commands.length)
			{
				return jumps;
			}
		}
	}

}
