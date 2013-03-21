package net.nsquared.playground.swingoflife;

import net.nsquared.playground.swingoflife.enums.CellState;
import net.nsquared.playground.swingoflife.services.RuleExecutor;
import net.nsquared.playground.swingoflife.utils.ConwayUtils;

import org.apache.tapestry5.ioc.Registry;

/**
 * Universe - Main class of GoL. Cell container, in charge of advancing
 * generations.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 * 
 */
public class Universe {

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
	public Universe(final int rows, final int columns, final Registry registry) {

		if (rows < 8 || columns < 8)
			throw new RuntimeException(
					"Size of the universe must be at least 8 x 8!");

		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		this.registry = registry;
		this.cellGrid = new Cell[rows][columns];
		initializeCells();
	}

	/**
	 * Convenience method for initializing cells in the grid
	 */
	private void initializeCells() {

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				Cell newCell = new Cell(registry.getService(RuleExecutor.class));
				newCell.setNeighbours(ConwayUtils.setupNeighbours(this, i, j));
				cellGrid[i][j] = newCell;
			}
		}

	}

	/**
	 * @param x - x coordinate in the cell grid
	 * @param y - y coordinate in the cell grid
	 * @return {@link Cell} at given coordinates
	 */
	public Cell getCellAt(final int x, final int y) {
		return cellGrid[x][y];
	}

	/**
	 * @return number of rows in the cell matrix
	 */
	public int getNumberOfRows() {
		return this.numberOfRows;
	}

	/**
	 * @return number of columns in the cell matrix
	 */
	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}

	/**
	 * Main method of the GoL implementation. Advances the universe to its next
	 * state.
	 * 
	 * @return true
	 */
	public boolean tick() {

		boolean universeChanged = false;

		/*
		 * Execute rules on all of the cells. Rules enqueue the next cell state.
		 */
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				Cell tmpCell = cellGrid[i][j];
				tmpCell.executeRules();
			}
		}

		/*
		 * Update states of all the cells. If the state is updated,
		 * universeChanged flag is set to true.
		 */
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				Cell tmpCell = cellGrid[i][j];
				universeChanged |= tmpCell.updateState();
			}
		}

		return universeChanged;
	}

	/**
	 * Sets state of all the cells in the universe to INACTIVE
	 */
	public void deactivateAll() {
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				Cell tmpCell = cellGrid[i][j];
				tmpCell.setState(CellState.INACTIVE);
			}
		}
	}

}
