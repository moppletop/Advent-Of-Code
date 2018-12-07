package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.moppletop.aoc.AbstractDay;

public class Day201807 extends AbstractDay
{

	private static final int WORKERS = 5;
	private static final int TIME_TO_ASSEMBLE = 60;

	private Set<Character> _steps;
	private Map<Character, Set<Character>> _stepsRequired;

	public Day201807() throws IOException
	{
		super(2018, 7);
	}

	@Override
	protected void preSolve()
	{
		_steps = new TreeSet<>();
		_stepsRequired = new HashMap<>();

		for (String line : inputList())
		{
			String[] comp = line.split(" ");
			char first = comp[1].charAt(0), second = comp[7].charAt(0);

			_steps.add(first);
			_steps.add(second);
			_stepsRequired.computeIfAbsent(second, k -> new TreeSet<>()).add(first);
		}
	}

	@Override
	protected Object solvePart1()
	{
		Set<Character> steps = new TreeSet<>(_steps);
		List<Character> order = new ArrayList<>();

		while (!steps.isEmpty())
		{
			Iterator<Character> iterator = steps.iterator();

			stepLoop: while (iterator.hasNext())
			{
				Character step = iterator.next();

				Set<Character> requirements = _stepsRequired.get(step);

				if (requirements != null)
				{
					for (Character requirement : requirements)
					{
						if (!order.contains(requirement))
						{
							continue stepLoop;
						}
					}
				}

				order.add(step);
				iterator.remove();
				break;
			}
		}

		StringBuilder builder = new StringBuilder(order.size());
		order.forEach(builder::append);
		return builder;
	}

	@Override
	protected Object solvePart2()
	{
		Set<Character> steps = new TreeSet<>(_steps);
		Set<Character> order = new HashSet<>();
		Map<Character, Integer> timeStarted = new HashMap<>(WORKERS);
		int timeElapsed = 0;

		while (!steps.isEmpty() || !timeStarted.isEmpty())
		{
			final int timeElapsedF = timeElapsed;
			timeStarted.entrySet().removeIf(entry ->
			{
				Character step = entry.getKey();
				int startedAt = entry.getValue();
				int timeToAssemble = step - 'A' + TIME_TO_ASSEMBLE + 1;

				if (timeElapsedF - startedAt >= timeToAssemble)
				{
					order.add(step);
					steps.remove(step);
					return true;
				}

				return false;
			});

			Iterator<Character> iterator = steps.iterator();

			stepLoop: while (iterator.hasNext())
			{
				if (timeStarted.size() == WORKERS)
				{
					break;
				}

				Character step = iterator.next();

				Set<Character> requirements = _stepsRequired.get(step);

				if (requirements != null)
				{
					for (Character requirement : requirements)
					{
						if (!order.contains(requirement))
						{
							continue stepLoop;
						}
					}
				}

				iterator.remove();
				timeStarted.put(step, timeElapsed);

				if (timeStarted.size() == WORKERS)
				{
					break;
				}
			}

			timeElapsed++;
		}

		return timeElapsed - 1;
	}
}
