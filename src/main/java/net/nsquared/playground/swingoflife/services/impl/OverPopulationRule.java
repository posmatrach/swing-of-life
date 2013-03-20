package net.nsquared.playground.swingoflife.services.impl;

import net.nsquared.playground.swingoflife.Cell;
import net.nsquared.playground.swingoflife.enums.CellState;
import net.nsquared.playground.swingoflife.services.ConwayRule;

/**
 * Rule of over-population
 * Any live cell with more than three live neighbours dies, as if by overcrowding
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class OverPopulationRule implements ConwayRule {

	public void apply(Cell cell) {
		
		if (cell.getNumberOfActiveNeighbours() > 3) {
			cell.enqueueState(CellState.INACTIVE);
		}

	}

}
