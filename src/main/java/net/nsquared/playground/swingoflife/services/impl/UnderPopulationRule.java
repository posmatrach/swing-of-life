package net.nsquared.playground.swingoflife.services.impl;

import net.nsquared.playground.swingoflife.Cell;
import net.nsquared.playground.swingoflife.enums.CellState;
import net.nsquared.playground.swingoflife.services.ConwayRule;

public class UnderPopulationRule implements ConwayRule {

	public void apply(Cell cell) {
		
		if (cell.getNumberOfActiveNeighbours() < 2) {
			cell.enqueueState(CellState.INACTIVE);
		}
	}

}
