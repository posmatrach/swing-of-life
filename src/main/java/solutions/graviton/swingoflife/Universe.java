package solutions.graviton.swingoflife;

import org.apache.tapestry5.ioc.Registry;

import com.sun.istack.internal.NotNull;

import solutions.graviton.swingoflife.enums.CellState;
import solutions.graviton.swingoflife.services.Context;
import solutions.graviton.swingoflife.services.ConwayRule;

/**
 * Universe - Main class of GoL. Cell container, in charge of advancing
 * generations.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 * 
 */
public class Universe implements Context
{

	private final Cell[][] cellGrid;

	private final int numberOfRows;

	private final int numberOfColumns;

	private final Registry registry;

	/**
	 * Universe constructor.
	 * 
	 * @param rows - number of rows of the cell matrix
	 * @param columns - number of columns of the cell matrix
	 * @param executor - {@link RuleExecutor} rule set for the GoL
	 */
	public Universe(final int rows, final int columns, final Registry registry)
	{

		if(rows < 8 || columns < 8)
			throw new RuntimeException("Size of the universe must be at least 8 x 8!");

		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		this.registry = registry;
		this.cellGrid = new Cell[rows][columns];
		initializeCells();
	}

	/**
	 * Convenience method for initializing cells in the grid
	 */
	private void initializeCells()
	{

		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				ConwayRule defaultRuleExecutor = registry.getService("defaultRuleExecutor", ConwayRule.class);
				Cell newCell = new Cell(new Coordinates(i, j), this, defaultRuleExecutor);
				cellGrid[i][j] = newCell;
			}
		}

	}

	/**
	 * @param x - x coordinate in the cell grid
	 * @param y - y coordinate in the cell grid
	 * @return {@link Cell} at given coordinates
	 */
	public Cell getCellAt(final int x, final int y)
	{
		return cellGrid[x][y];
	}

	/**
	 * @return number of rows in the cell matrix
	 */
	public int getNumberOfRows()
	{
		return this.numberOfRows;
	}

	/**
	 * @return number of columns in the cell matrix
	 */
	public int getNumberOfColumns()
	{
		return this.numberOfColumns;
	}

	/**
	 * Main method of the GoL implementation. Advances the universe to its next
	 * state.
	 * 
	 * @return true
	 */
	public boolean tick()
	{

		boolean universeChanged = false;

		/*
		 * Execute rules on all of the cells. Rules enqueue the next cell state.
		 */
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				Cell tmpCell = cellGrid[i][j];
				tmpCell.executeRules();
			}
		}

		/*
		 * Update states of all the cells. If the state is updated,
		 * universeChanged flag is set to true.
		 */
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				Cell tmpCell = cellGrid[i][j];
				universeChanged |= tmpCell.updateState();
			}
		}

		return universeChanged;
	}

	/**
	 * Sets state of all the cells in the universe to INACTIVE
	 */
	public void deactivateAll()
	{
		for(int i = 0; i < numberOfRows; i++)
		{
			for(int j = 0; j < numberOfColumns; j++)
			{
				Cell tmpCell = cellGrid[i][j];
				tmpCell.setState(CellState.INACTIVE);
			}
		}
	}
	
	@Override
	public Cell getCellAt(@NotNull Coordinates coordinates)
	{
		int x = coordinates.getX();
		int y = coordinates.getY();
		if(x >= 0 && x < numberOfRows && y >= 0 && y < numberOfColumns)
			return getCellAt(coordinates.getX(), coordinates.getY());
		return null;
	}
}
