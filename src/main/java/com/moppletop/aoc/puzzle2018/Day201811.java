package com.moppletop.aoc.puzzle2018;

import java.io.IOException;

import com.moppletop.aoc.AbstractDay;

public class Day201811 extends AbstractDay
{

	private static final int SIZE = 300;

	private int[][] _grid;

	public Day201811() throws IOException
	{
		super(2018, 11);
	}

	@Override
	protected void preSolve()
	{
		_grid = new int[SIZE][SIZE];
		int gridLength = _grid.length;
		int serialNumber = Integer.parseInt(inputFirstLine());

		for (int x = 0; x < gridLength; x++)
		{
			int[] rows = _grid[x];

			for (int y = 0; y < gridLength; y++)
			{
				int xCord = x + 1, yCord = y + 1;

				int rackId = xCord + 10;
				int powerLevel = rackId * yCord;
				powerLevel += serialNumber;
				powerLevel *= rackId;
				powerLevel = (powerLevel / 100) % 10;
				rows[y] = powerLevel - 5;
			}
		}
	}

	@Override
	protected Object solvePart1()
	{
		int gridLength = _grid.length - 2;
		int largestXCord = 0, largestYCord = 0;
		int largestPower = Integer.MIN_VALUE;
		int total;

		for (int x = 1; x < gridLength; x++)
		{
			for (int y = 1; y < gridLength; y++)
			{
				total = getPower(x, y, 3);

				if (total > largestPower)
				{
					largestXCord = x;
					largestYCord = y;
					largestPower = total;
				}
			}
		}

		return (largestXCord + 1) + "," + (largestYCord + 1);
	}

	@Override
	protected Object solvePart2()
	{
		int gridLength;
		int largestXCord = 0, largestYCord = 0;
		int largestSize = 0;
		int largestPower = Integer.MIN_VALUE;
		int total;

		// It's unlikely to be above 20 due to that examples shown
		for (int s = 1; s < 20; s++)
		{
			gridLength = SIZE + 1 - s;

			for (int x = 1; x < gridLength; x++)
			{
				for (int y = 1; y < gridLength; y++)
				{
					total = getPower(x, y, s);

					if (total > largestPower)
					{
						largestXCord = x;
						largestYCord = y;
						largestSize = s;
						largestPower = total;
					}
				}
			}
		}

		return (largestXCord + 1) + "," + (largestYCord + 1)+ "," + largestSize;
	}

	private int getPower(int x, int y, int size)
	{
		int total = 0;

		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				total += _grid[x + i][y + j];
			}
		}

		return total;
	}
}
