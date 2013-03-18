package net.nsquared.swingoflife.swingoflife.enums;


/**
 * Enum representing possible states of the cell (active and inactive)
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public enum CellState {
	
	ACTIVE {
		@Override
		public boolean isActive() {
			return true;
		}
	}, 
	
	INACTIVE {
		@Override
		public boolean isActive() {
			return false;
		}
	};
	
	public abstract boolean isActive();

}
