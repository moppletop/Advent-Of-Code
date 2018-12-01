package com.moppletop.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.moppletop.aoc.util.CircularList;

public abstract class AbstractDay
{

	private final List<String> _puzzleInput;

	public AbstractDay(int year, int day) throws IOException
	{
		File file = new File("puzzleInput" + File.separator + year + File.separator + day + ".dat");
		_puzzleInput = Files.readAllLines(file.toPath());

		preSolve();

		System.out.println(solvePart1());
		System.out.println(solvePart2());
	}

	protected abstract void preSolve();

	protected abstract Object solvePart1();

	protected abstract Object solvePart2();

	protected final List<String> inputList()
	{
		return _puzzleInput;
	}

	protected final CircularList<String> inputCircularList()
	{
		return new CircularList<>(_puzzleInput);
	}

	protected final String inputFirstLine()
	{
		return _puzzleInput.get(0);
	}

	protected final String inputJoined(String delimiter)
	{
		return String.join(delimiter, _puzzleInput);
	}
}
