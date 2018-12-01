package com.moppletop.aoc.puzzle2017;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.moppletop.aoc.AbstractDay;

public class Day201704 extends AbstractDay
{

	public Day201704() throws IOException
	{
		super(2017, 4);
	}

	@Override
	protected void preSolve()
	{

	}

	@Override
	protected Object solvePart1()
	{
		return String.valueOf(inputList().stream()
				.filter(this::hasDuplicateWords)
				.count());
	}

	@Override
	protected Object solvePart2()
	{
		return String.valueOf(inputList().stream()
				.filter(this::hasAnagram)
				.count());
	}

	private boolean hasDuplicateWords(String passphrase)
	{
		String[] words = passphrase.split(" ");
		Set<String> seen = new HashSet<>(words.length);

		for (String word : words)
		{
			if (!seen.add(word))
			{
				return false;
			}
		}

		return true;
	}

	private boolean hasAnagram(String passphrase)
	{
		String[] words = passphrase.split(" ");
		Set<Map<Character, Integer>> wordsMap = new HashSet<>(words.length);

		for (String word : words)
		{
			Map<Character, Integer> charsSeen = new HashMap<>();

			for (char ch : word.toCharArray())
			{
				charsSeen.put(ch, charsSeen.getOrDefault(ch, 0) + 1);
			}

			if (!wordsMap.add(charsSeen))
			{
				return false;
			}
		}

		return true;
	}
}
