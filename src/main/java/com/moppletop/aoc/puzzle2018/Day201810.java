package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.moppletop.aoc.AbstractDay;

public class Day201810 extends AbstractDay
{

	private int _seconds;

	public Day201810() throws IOException
	{
		super(2018, 10);
	}

	@Override
	protected void preSolve()
	{
		List<Point> points = new ArrayList<>(inputList().size());

		for (String line : inputList())
		{
			line = line.replaceAll("[^-|0-9]+", " ").trim();
			String[] comp = line.split(" ");

			int x = Integer.parseInt(comp[0]), y = Integer.parseInt(comp[1]);
			int dx = Integer.parseInt(comp[2]), dy = Integer.parseInt(comp[3]);

			points.add(new Point(x, y, dx, dy));
		}

		int seconds = 0;

		while (true)
		{
			seconds++;
			int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

			for (Point point : points)
			{
				point.X += point.Dx;
				point.Y += point.Dy;

				minX = Math.min(point.X, minX);
				minY = Math.min(point.Y, minY);
				maxX = Math.max(point.X, maxX);
				maxY = Math.max(point.Y, maxY);
			}

			if (maxX - minX < 100 && maxY - minY < 10)
			{
				for (int x = minX; x <= maxX; x++)
				{
					for (int y = minY; y <= maxY; y++)
					{
						char ch = '.';

						for (Point light : points)
						{
							if (x == light.X && y == light.Y)
							{
								ch = '#';
							}
						}

						System.out.print(ch);
					}

					System.out.println();
				}

				_seconds = seconds;
				break;
			}
		}
	}

	@Override
	protected Object solvePart1()
	{
		return null;
	}

	@Override
	protected Object solvePart2()
	{
		return _seconds;
	}

	private class Point
	{
		int X, Y;
		int Dx, Dy;

		Point(int x, int y, int dx, int dy)
		{
			X = x;
			Y = y;
			Dx = dx;
			Dy = dy;
		}
	}
}
