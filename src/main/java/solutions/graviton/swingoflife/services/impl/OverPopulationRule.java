package solutions.graviton.swingoflife.services.impl;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.ConwayRule;

/**
 * Rule of over-population
 * Any live cell with more than three live neighbours dies, as if by overcrowding
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class OverPopulationRule implements ConwayRule
{
	public boolean apply(Cell cell)
	{
		if(cell.getNumberOfActiveNeighbours() > 3)
		{
			cell.enqueueState(CellState.INACTIVE);
			return true;
		}
		return false;
	}
}
