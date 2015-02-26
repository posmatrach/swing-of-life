package solutions.graviton.swingoflife.services.impl;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.ConwayRule;

/**
 * Rule of reproduction.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class ReproductionRule implements ConwayRule
{
	public void apply(Cell cell)
	{
		if(cell.getNumberOfActiveNeighbours() == 3)
			cell.enqueueState(CellState.ACTIVE);
	}
}
