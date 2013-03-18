package net.nsquared.swingoflife.swingoflife;

import java.util.Set;

import net.nsquared.swingoflife.swingoflife.enums.CellState;
import net.nsquared.swingoflife.swingoflife.services.RuleExecutor;

import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;

public class Cell {
	
	private Set<Cell> neighbours;
	
	private CellState cellState;
	
	private CellState queuedState;
	
	public void accept(RuleExecutor applicator) {
		
	}
	
	public boolean isActive() {
		/** 
		 * TODO Implement! (Consider possibility of delegation of the logic to CellState object.)
		 */
		return false;
	}
	
	public int getNumberOfActiveNeighbours() {
		
		return F.flow(neighbours).filter(new Predicate<Cell>() {

			public boolean accept(Cell element) {
				return element.isActive();
			}
			
		}).count();
		
	}
	
	public void enqueueState(final CellState state) {
		this.queuedState = state;
	}
	
}
