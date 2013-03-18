package net.nsquared.swingoflife.swingoflife.services.impl;

import net.nsquared.swingoflife.swingoflife.services.CellState;

public class InactiveCellState implements CellState {

	public boolean isActive() {
		return false;
	}

}
