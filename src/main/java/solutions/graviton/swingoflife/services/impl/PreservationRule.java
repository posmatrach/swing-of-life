package solutions.graviton.swingoflife.services.impl;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.ConwayRule;

/**
 * Preservation Rule
 * "Any live cell with two or three live neighbours lives on to the next generation."
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class PreservationRule implements ConwayRule
{
	public boolean apply(Cell cell)
	{
		final int activeNeighbours = cell.getNumberOfActiveNeighbours();

		if(activeNeighbours == 2 || activeNeighbours == 3)
		{
			/**
			 * Technically no need to do anything here, since we know that state is already Active,
			 * however for consistency sake, we enqueue Active state for next generation.
			 */
			cell.enqueueState(CellState.ACTIVE);
			return true;
		}
		return false;
	}
}
