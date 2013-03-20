package net.nsquared.playground.swingoflife;

import java.util.HashSet;
import java.util.Set;

import net.nsquared.playground.swingoflife.enums.CellState;
import net.nsquared.playground.swingoflife.services.RuleExecutor;

import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;

/**
 * 
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 * 
 */
public class Cell {

	private final Set<Cell> neighbours;

	private CellState currentState;

	private CellState queuedState;

	private RuleExecutor ruleExecutor;

	public Cell(final RuleExecutor ruleExecutor) {
		this.neighbours = new HashSet<Cell>();
		this.currentState = CellState.INACTIVE;
		this.queuedState = null;
		this.ruleExecutor = ruleExecutor;
	}

	public void acceptRuleExecutor(final RuleExecutor ruleExecutor) {
		this.ruleExecutor = ruleExecutor;
	}

	public boolean isActive() {
		return currentState.isActive();
	}

	public int getNumberOfActiveNeighbours() {

		return F.flow(neighbours).filter(new Predicate<Cell>() {

			public boolean accept(Cell cell) {
				return cell.isActive();
			}

		}).count();

	}

	public void enqueueState(final CellState newState) {
		if (newState != this.currentState)
			this.queuedState = newState;
	}

	public CellState getState() {
		return this.currentState;
	}

	public void setState(final CellState newState) {
		this.currentState = newState;
	}

	public void addNeighbour(final Cell neighbour) {
		neighbours.add(neighbour);
	}

	public boolean updateState() {
		boolean stateChanged = false;

		if (queuedState != null) {
			this.currentState = queuedState;
			this.queuedState = null;
			stateChanged = true;
		}
		return stateChanged;
	}

	public void executeRules() {
		this.ruleExecutor.execute(this);
	}

	public void toggleState() {
		this.currentState = this.currentState.toggle();
	}

}
