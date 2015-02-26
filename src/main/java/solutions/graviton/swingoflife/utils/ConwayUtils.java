package solutions.graviton.swingoflife.utils;

import java.util.HashSet;
import java.util.Set;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.Universe;

public class ConwayUtils
{
	/**
	 * @param universe
	 * @param x
	 * @param y
	 * @return
	 */
	public static final Set<Cell> setupNeighbours(final Universe universe, final int x, final int y)
	{
		//TODO Do I need it?
		Set<Cell> neighbours = new HashSet<Cell>();

		for(int i = x - 1; i <= x + 1; i++)
		{
			for(int j = y - 1; j <= y + 1; j++)
			{
				if(i >= 0 && j >= 0 && i != x && j != y
					&& i < universe.getNumberOfRows()
					&& j < universe.getNumberOfColumns())
					neighbours.add(universe.getCellAt(i, j));
			}
		}

		return neighbours;
	}
}
