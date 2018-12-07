package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.moppletop.aoc.AbstractDay;

public class Day201806 extends AbstractDay
{

	private static final char CONTESTED_CHAR = '.';
	private static final int SAFE_DISTANCE = 10000;

	private Location[] _locations;
	private int _maxX, _maxY;
	private char[][] _grid;

	public Day201806() throws IOException
	{
		super(2018, 6);
	}

	@Override
	protected void preSolve()
	{
		_maxX = Integer.MIN_VALUE;
		_maxY = Integer.MIN_VALUE;
		_locations = new Location[inputList().size()];
		int i = 0;

		for (String line : inputList())
		{
			String[] comps = line.split(", ");
			int x = Integer.parseInt(comps[1]) + 1;
			int y = Integer.parseInt(comps[0]) + 1;

			_locations[i] = new Location((char) ('A' + i), x, y);
			i++;

			if (x > _maxX)
			{
				_maxX = x;
			}

			if (y > _maxY)
			{
				_maxY = y;
			}
		}

		_maxX++;
		_maxY++;

		_grid = new char[_maxX][_maxY];
		List<Location> closest = new LinkedList<>();

		for (int x = 0; x < _grid.length; x++)
		{
			char[] row = _grid[x];

			for (int y = 0; y < row.length; y++)
			{
				closest.clear();
				int shortest = Integer.MAX_VALUE;

				for (Location location : _locations)
				{
					int distance = location.getDistanceTo(x, y);

					if (distance < shortest)
					{
						shortest = distance;

						closest.clear();
						closest.add(location);
					}
					else if (distance == shortest)
					{
						closest.add(location);
					}
				}

				if (closest.size() > 1)
				{
					_grid[x][y] = CONTESTED_CHAR;
				}
				else if (closest.size() == 1)
				{
					char id = closest.get(0).Id;
					_grid[x][y] = id;
				}
			}
		}
	}

	@Override
	protected Object solvePart1()
	{
		Map<Character, Integer> area = new HashMap<>(_locations.length);

		for (int x = 0; x < _maxX; x++)
		{
			for (int y = 0; y < _maxY; y++)
			{
				char id = _grid[x][y];

				if (id == CONTESTED_CHAR)
				{
					continue;
				}

				if (x == 0 || y == 0 || x == _maxX - 1 || y == _maxY - 1)
				{
					area.put(id, -1);
				}
				else
				{
					int currentArea = area.getOrDefault(id, 0);

					if (currentArea < 0)
					{
						continue;
					}

					area.put(id, currentArea + 1);
				}
			}
		}

		return area.values().stream()
				.mapToInt(value -> value)
				.max()
				.orElse(-1);
	}

	@Override
	protected Object solvePart2()
	{
		int area = 0;

		for (int x = 0; x < _grid.length; x++)
		{
			char[] row = _grid[x];

			for (int y = 0; y < row.length; y++)
			{
				int distanceSum = 0;

				for (Location location : _locations)
				{
					distanceSum += location.getDistanceTo(x, y);
				}

				if (distanceSum < SAFE_DISTANCE)
				{
					area++;
				}
			}
		}

		return area;
	}

	private class Location
	{
		final char Id;
		final int X;
		final int Y;

		Location(char id, int x, int y)
		{
			Id = id;
			X = x;
			Y = y;
		}

		int getDistanceTo(int x, int y)
		{
			return Math.abs(X - x) + Math.abs(Y - y);
		}

		@Override
		public String toString()
		{
			return "Location={id=" + Id + ", x=" + X + ", y=" + Y;
		}
	}
}
