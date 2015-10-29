package solutions.graviton.swingoflife;

import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.Context;
import solutions.graviton.swingoflife.services.ConwayRule;

/**
 * Representation of one cell in the GoL
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 * 
 */
public class Cell
{
	
	private CellState currentState;

	private CellState queuedState;

	private ConwayRule ruleExecutor;
	
	private Coordinates coordinates;
	
	private Context context;

	public Cell(Coordinates coordinates, Context context, final ConwayRule ruleExecutor)
	{
		this.currentState = CellState.INACTIVE;
		this.queuedState = null;
		this.coordinates = coordinates;
		this.ruleExecutor = ruleExecutor;
		this.context = context;
	}

	public void acceptRuleExecutor(final ConwayRule ruleExecutor)
	{
		this.ruleExecutor = ruleExecutor;
	}

	public boolean isActive()
	{
		return currentState.isActive();
	}

	public int getNumberOfActiveNeighbours()
	{
		int activeCells = 0;
		for(CellEnvironment env : CellEnvironment.values())
		{
			Cell c = context.getCellAt(env.getAbsoluteCoordinates(coordinates));
			if(null != c && c.getState().equals(CellState.ACTIVE))
				activeCells++;
		}
		return activeCells;
	}

	public void enqueueState(final CellState newState)
	{
		if(newState != this.currentState)
			this.queuedState = newState;
	}

	public CellState getState()
	{
		return this.currentState;
	}

	public void setState(final CellState newState)
	{
		this.currentState = newState;
	}

	public boolean updateState()
	{
		boolean stateChanged = false;

		if(queuedState != null)
		{
			this.currentState = queuedState;
			this.queuedState = null;
			stateChanged = true;
		}
		return stateChanged;
	}

	public void executeRules()
	{
		this.ruleExecutor.apply(this);
	}

	public void toggleState()
	{
		this.currentState = this.currentState.toggle();
	}
}
