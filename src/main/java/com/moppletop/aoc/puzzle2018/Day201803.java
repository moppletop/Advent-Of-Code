package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.moppletop.aoc.AbstractDay;

public class Day201803 extends AbstractDay
{

	private int[][] _fabric;
	private Set<Claim> _claims;

	public Day201803() throws IOException
	{
		super(2018, 3);
	}

	@Override
	protected void preSolve()
	{
		int sizeX = 0, sizeY = 0;
		_claims = new HashSet<>();

		for (String line : inputList())
		{
			line = line.replaceAll("[#@,:x ]+", " ").trim();

			String[] args = line.split(" ");

			int id = Integer.parseInt(args[0]);
			int startX = Integer.parseInt(args[1]), startY = Integer.parseInt(args[2]);
			int width = Integer.parseInt(args[3]), height = Integer.parseInt(args[4]);
			int endX = startX + width, endY = startY + height;

			if (endX > sizeX)
			{
				sizeX = endX;
			}
			else if (endY > sizeY)
			{
				sizeY = endY;
			}

			_claims.add(new Claim(id, startX, startY, endX, endY));
		}

		_fabric = new int[sizeX][sizeY];
	}

	@Override
	protected Object solvePart1()
	{
		int overlaps = 0;

		for (Claim claim : _claims)
		{
			for (int x = claim.StartX; x < claim.EndX; x++)
			{
				for (int y = claim.StartY; y < claim.EndY; y++)
				{
					if (_fabric[x][y]++ == 1)
					{
						overlaps++;
					}
				}
			}
		}

		return overlaps;
	}

	@Override
	protected Object solvePart2()
	{
		claimLoop: for (Claim claim : _claims)
		{
			for (int x = claim.StartX; x < claim.EndX; x++)
			{
				for (int y = claim.StartY; y < claim.EndY; y++)
				{
					if (_fabric[x][y] > 1)
					{
						continue claimLoop;
					}
				}
			}

			return claim.Id;
		}

		return null;
	}

	private class Claim
	{
		int Id;
		int StartX, StartY;
		int EndX, EndY;

		Claim(int id, int startX, int startY, int endX, int endY)
		{
			Id = id;
			StartX = startX;
			StartY = startY;
			EndX = endX;
			EndY = endY;
		}
	}
}
