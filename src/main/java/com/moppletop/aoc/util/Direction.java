package com.moppletop.aoc.util;

public enum Direction
{

	RIGHT(0, 1),
	UP(-1, 0),
	LEFT(0, -1),
	DOWN(1, 0),

	UP_RIGHT(UP._modX + RIGHT._modX, UP._modY + RIGHT._modY, true),
	UP_LEFT(UP._modX + LEFT._modX, UP._modY + LEFT._modY, true),
	DOWN_RIGHT(DOWN._modX + RIGHT._modX, DOWN._modY + RIGHT._modY, true),
	DOWN_LEFT(DOWN._modX + LEFT._modX, DOWN._modY + LEFT._modY, true);

	private static final Direction[] NON_DIAGONALS = new Direction[]
			{
					RIGHT,
					UP,
					LEFT,
					DOWN
			};

	public static Direction[] nonDiagonals()
	{
		return NON_DIAGONALS;
	}

	private final int _modX;
	private final int _modY;
	private final boolean _diagonal;

	Direction(int modX, int modY)
	{
		this(modX, modY, false);
	}

	Direction(int modX, int modY, boolean diagonal)
	{
		_modX = modX;
		_modY = modY;
		_diagonal = diagonal;
	}

	public int getModX()
	{
		return _modX;
	}

	public int getModY()
	{
		return _modY;
	}

	public Direction next(boolean clockwise)
	{
		return nonDiagonals()[(ordinal() + (clockwise ? -1 : 1)) % nonDiagonals().length];
	}

	public boolean isHorizontal()
	{
		return this == RIGHT || this == LEFT;
	}
}
