package com.moppletop.aoc.puzzle2018;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.moppletop.aoc.AbstractDay;

public class Day201804 extends AbstractDay
{

	private DateFormat _dateFormat;
	private List<Event> _events;

	public Day201804() throws IOException
	{
		super(2018, 4);
	}

	@Override
	protected void preSolve()
	{
		_dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm]");

		_events = new ArrayList<>(inputList().size());
		int lastId = -1;
		List<String> inputOrdered = new ArrayList<>(inputList());
		inputOrdered.sort(Comparator.comparingLong(s ->
		{
			try
			{
				return _dateFormat.parse(s).getTime();
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				return 0L;
			}
		}));

		for (String line : inputOrdered)
		{
			String[] split = line.split(" ");
			String action = split[2];
			boolean awake = false, starting = false;

			switch (action)
			{
				case "Guard":
					lastId = Integer.parseInt(split[3].substring(1));
					awake = true;
					starting = true;
					break;
				case "falls":
					awake = false;
					break;
				case "wakes":
					awake = true;
					break;
			}

			if (lastId == -1)
			{
				continue;
			}

			try
			{
				_events.add(new Event(_dateFormat.parse(line), lastId, awake, starting));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}

		_events.sort(Comparator.comparing(event -> event.When));
	}

	@Override
	protected Object solvePart1()
	{
		Map<Integer, Integer> totalSlept = new HashMap<>();
		List<Shift> shifts = new ArrayList<>();
		Shift currShift = new Shift(_events.get(0).GuardId);
		shifts.add(currShift);

		for (int i = 0; i < _events.size(); i++)
		{
			Event curr = _events.get(i);

			if (curr.Starting)
			{
				currShift = new Shift(curr.GuardId);
				shifts.add(currShift);
			}
			else if (curr.Awake)
			{
				Event previous = _events.get(i - 1);
				int start = previous.When.getMinutes(), end = curr.When.getMinutes();
				int difference = end - start;

				for (int j = start; j < end; j++)
				{
					currShift.MinutesSlept.add(j);
				}

				totalSlept.put(currShift.GuardId, totalSlept.getOrDefault(currShift.GuardId, 0) + difference);
			}
		}

		int guardId = totalSlept.entrySet().stream()
				.max(Comparator.comparingInt(Entry::getValue))
				.get()
				.getKey();
		Map<Integer, Integer> timesSlept = new HashMap<>();

		for (Shift shift : shifts)
		{
			if (shift.GuardId != guardId)
			{
				continue;
			}

			for (int minute : shift.MinutesSlept)
			{
				timesSlept.put(minute, timesSlept.getOrDefault(minute, 0) + 1);
			}
		}

		int chosenMinute = timesSlept.entrySet().stream()
				.max(Comparator.comparingInt(Entry::getValue))
				.get()
				.getKey();

		return guardId * chosenMinute;
	}

	@Override
	protected Object solvePart2()
	{
		Shift currShift = new Shift(_events.get(0).GuardId);

		Map<Integer, Map<Integer, Integer>> sleptMinutes = new HashMap<>();

		for (int i = 0; i < _events.size(); i++)
		{
			Event curr = _events.get(i);

			if (curr.Starting)
			{
				currShift = new Shift(curr.GuardId);
			}
			else if (curr.Awake)
			{
				Event previous = _events.get(i - 1);
				int start = previous.When.getMinutes(), end = curr.When.getMinutes();
				Map<Integer, Integer> slept = sleptMinutes.computeIfAbsent(curr.GuardId, k -> new HashMap<>());

				for (int j = start; j < end; j++)
				{
					slept.put(j, slept.getOrDefault(j, 0) + 1);
					currShift.MinutesSlept.add(j);
				}
			}
		}

		int guard = 0;
		int minute = 0, most = 0;

		for (Entry<Integer, Map<Integer, Integer>> entry : sleptMinutes.entrySet())
		{
			Map<Integer, Integer> map = entry.getValue();

			for (Entry<Integer, Integer> sleptEntry : map.entrySet())
			{
				if (sleptEntry.getValue() > most)
				{
					minute = sleptEntry.getKey();
					most = sleptEntry.getValue();
					guard = entry.getKey();
				}
			}
		}

		return guard * minute;
	}

	private class Event
	{
		Date When;
		int GuardId;
		boolean Awake, Starting;

		Event(Date when, int guardId, boolean awake, boolean starting)
		{
			When = when;
			GuardId = guardId;
			Awake = awake;
			Starting = starting;
		}
	}

	private class Shift
	{
		int GuardId;
		Set<Integer> MinutesSlept;

		Shift(int guardId)
		{
			GuardId = guardId;
			MinutesSlept = new HashSet<>();
		}
	}
}
