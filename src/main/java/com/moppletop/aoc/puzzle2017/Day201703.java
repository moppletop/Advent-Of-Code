package com.moppletop.aoc.puzzle2017;

import java.io.IOException;

import com.moppletop.aoc.AbstractDay;
import com.moppletop.aoc.util.Direction;

public class Day201703 extends AbstractDay
{

	private int _size, _input;

	public Day201703() throws IOException
	{
		super(2017, 3);
	}

	@Override
	protected void preSolve()
	{
		_input = Integer.parseInt(inputFirstLine());
		_size = (int) (Math.sqrt(_input) + 1);
	}

	@Override
	protected Object solvePart1()
	{
		int start = _size / 2;
		int x = start, y = start;
		Direction direction = Direction.RIGHT;
		int toTake = 1, hasTaken = 0;

		for (int i = 1; i < _input; i++)
		{
			if (hasTaken == toTake)
			{
				hasTaken = 0;
				direction = direction.next(false);

				if (direction.isHorizontal())
				{
					toTake++;
				}
			}

			x += direction.getModX();
			y += direction.getModY();

			hasTaken++;
		}

		return Math.abs(x - start) + Math.abs(y - start);
	}

	@Override
	protected Object solvePart2()
	{
		int[][] grid = new int[_size][_size];
		int start = _size / 2;
		int x = start, y = start;
		Direction direction = Direction.RIGHT;
		int toTake = 1, hasTaken = 0;

		for (int i = 1; ; i++)
		{
			int value = i == 1 ? i : 0;

			for (Direction other : Direction.values())
			{
				int newX = x + other.getModX(), newY = y + other.getModY();

				if (newX >= grid.length || newY >= grid[newX].length)
				{
					continue;
				}

				value += grid[x + other.getModX()][y + other.getModY()];
			}

			grid[x][y] = value;

			if (value > _input)
			{
				return value;
			}

			if (hasTaken == toTake)
			{
				hasTaken = 0;
				direction = direction.next(false);

				if (direction.isHorizontal())
				{
					toTake++;
				}
			}

			x += direction.getModX();
			y += direction.getModY();

			hasTaken++;
		}
	}
}
