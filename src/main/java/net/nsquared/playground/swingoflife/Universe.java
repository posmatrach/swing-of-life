package net.nsquared.playground.swingoflife;

import net.nsquared.playground.swingoflife.services.RuleExecutor;

public class Universe {

	private final Cell[][] cellGrid;

	private final int numberOfRows;

	private final int numberOfColumns;

	private final RuleExecutor ruleExecutor;

	public Universe(final int rows, final int columns,
			final RuleExecutor executor) {

		if (rows < 8 || columns < 8)
			throw new RuntimeException(
					"Size of the universe must be at least 8 x 8!");

		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		this.ruleExecutor = executor;
		this.cellGrid = new Cell[rows][columns];
	}

	public Cell getCellAt(final int x, final int y) {
		return cellGrid[x][y];
	}

	public int getNumberOfRows() {
		return this.numberOfRows;
	}

	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}

}
