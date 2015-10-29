package solutions.graviton.swingoflife.services.impl;

import solutions.graviton.swingoflife.Cell;
import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.ConwayRule;

public class UnderPopulationRule implements ConwayRule
{
	public boolean apply(Cell cell)
	{
		if(cell.getNumberOfActiveNeighbours() < 2)
		{
			cell.enqueueState(CellState.INACTIVE);
			return true;
		}
		return false;
	}
}
