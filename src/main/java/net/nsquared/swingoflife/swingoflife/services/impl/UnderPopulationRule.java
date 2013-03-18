package net.nsquared.swingoflife.swingoflife.services.impl;

import net.nsquared.swingoflife.swingoflife.Cell;
import net.nsquared.swingoflife.swingoflife.enums.CellState;
import net.nsquared.swingoflife.swingoflife.services.ConwayRule;

public class UnderPopulationRule implements ConwayRule {

	public void apply(Cell cell) {
		
		if (cell.getNumberOfActiveNeighbours() < 2) {
			cell.enqueueState(CellState.INACTIVE);
		}
	}

}
