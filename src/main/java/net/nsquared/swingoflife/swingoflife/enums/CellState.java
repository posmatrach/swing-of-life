package net.nsquared.swingoflife.swingoflife.enums;


/**
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
