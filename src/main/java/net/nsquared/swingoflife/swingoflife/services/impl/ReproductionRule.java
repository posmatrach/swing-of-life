package net.nsquared.swingoflife.swingoflife.services.impl;

import net.nsquared.swingoflife.swingoflife.Cell;
import net.nsquared.swingoflife.swingoflife.enums.CellState;
import net.nsquared.swingoflife.swingoflife.services.ConwayRule;

/**
 * Rule of reproduction.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class ReproductionRule implements ConwayRule {

	public void apply(Cell cell) {
		
		if (cell.getNumberOfActiveNeighbours() == 3) {
			cell.enqueueState(CellState.ACTIVE);
		}

	}

}
