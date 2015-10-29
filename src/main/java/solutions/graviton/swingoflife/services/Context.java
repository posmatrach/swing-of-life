package solutions.graviton.swingoflife.services;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.Coordinates;

public interface Context
{
	Cell getCellAt(Coordinates coordinates);
}
