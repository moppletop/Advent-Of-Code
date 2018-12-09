package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.moppletop.aoc.AbstractDay;

public class Day201809 extends AbstractDay
{

	private int _players;
	private int _lastWorth;

	public Day201809() throws IOException
	{
		super(2018, 9);
	}

	@Override
	protected void preSolve()
	{
		String[] comps = inputFirstLine().split(" ");
		_players = Integer.parseInt(comps[0]);
		_lastWorth = Integer.parseInt(comps[6]);
	}

	@Override
	protected Object solvePart1()
	{
		return playGame(_lastWorth);
	}

	@Override
	protected Object solvePart2()
	{
		return playGame(_lastWorth * 100);
	}

	private long playGame(int lastWorth)
	{
		LinkedList<Integer> marbles = new LinkedList<>();
		Map<Integer, Long> scores = new HashMap<>(_players);

		marbles.add(0);
		int placedWorth = 1;
		int player = 0;

		while (placedWorth < lastWorth)
		{
			if (placedWorth % 23 == 0)
			{
				int points = placedWorth;

				for (int i = 0; i < 6; i++)
				{
					marbles.addLast(marbles.removeFirst());
				}

				points += marbles.removeFirst();

				scores.put(player, scores.getOrDefault(player, 0L) + points);
			}
			else
			{
				for (int i = 0; i < 2; i++)
				{
					marbles.addFirst(marbles.removeLast());
				}

				marbles.addLast(placedWorth);
			}

			player = (player + 1) % _players;
			placedWorth++;
		}

		return scores.values().stream()
				.mapToLong(value -> value)
				.max()
				.orElse(-1);
	}

}
