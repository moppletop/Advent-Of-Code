package com.moppletop.aoc.puzzle2017;

import java.io.IOException;
import java.util.Arrays;

import com.moppletop.aoc.AbstractDay;

public class Day201702 extends AbstractDay
{

	public Day201702() throws IOException
	{
		super(2017, 2);
	}

	@Override
	protected void preSolve()
	{

	}

	@Override
	protected Object solvePart1()
	{
		int checkSum = 0;

		for (String line : inputList())
		{
			String[] split = line.split("\t");
			int smallest = Integer.MAX_VALUE, largest = 0;

			for (String num : split)
			{
				int numInt = Integer.parseInt(num);

				if (numInt < smallest)
				{
					smallest = numInt;
				}

				if (numInt > largest)
				{
					largest = numInt;
				}
			}

			checkSum += (largest - smallest);
		}

		return checkSum;
	}

	@Override
	protected Object solvePart2()
	{
		int checkSum = 0;

		lineLoop: for (String line : inputList())
		{
			int[] nums = Arrays.stream(line.split("\t"))
					.mapToInt(Integer::parseInt)
					.toArray();

			for (int i = 0; i < nums.length; i++)
			{
				for (int j = 0; j < nums.length; j++)
				{
					if (i == j)
					{
						continue;
					}

					int a = Math.max(nums[i], nums[j]), b = Math.min(nums[i], nums[j]);

					if (a % b == 0)
					{
						checkSum += (a / b);
						continue lineLoop;
					}
				}
			}
		}

		return checkSum;
	}
}
