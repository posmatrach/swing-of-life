package net.nsquared.playground.swingoflife.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;

import net.nsquared.playground.swingoflife.Cell;
import net.nsquared.playground.swingoflife.Universe;
import net.nsquared.playground.swingoflife.enums.CellState;

@SuppressWarnings("serial")
public class UniverseCanvas extends Canvas {

	private Image offScreenImage;

	private Image backgroundImage;

	private final int cellSize;

	private final Universe universe;

	private final Image liveCellImage;

	private static final Color BACKGROUND_COLOR = Color.gray;

	private static final Color GRID_COLOR = BACKGROUND_COLOR.brighter();

	/**
	 * Constructs a UniverseCanvas.
	 * 
	 * @param universe the GoL universe
	 */
	public UniverseCanvas(Universe universe) {

		this.universe = universe;

		this.liveCellImage = new ImageIcon(
				UniverseCanvas.class.getResource("cell_image.png")).getImage();

		this.cellSize = liveCellImage.getWidth(this);

		setBackground(GRID_COLOR);

		addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when a mouse button has been pressed on a component.
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				toggleCellAt(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Cell cell = getCellAtPoint(e.getX(), e.getY());
				if (cell != null) {
					cell.setState(CellState.ACTIVE);
					repaint();
				}
			}
		});
	}

	private void toggleCellAt(int x, int y) {

		Cell cell = getCellAtPoint(x, y);
		if (cell != null) {
			cell.toggleState();
			repaint();
		}
	}

	private Cell getCellAtPoint(int x, int y) {
		Cell cell = null;

		int column = x / cellSize;
		int row = y / cellSize;
		final int numberOfColumns = universe.getNumberOfColumns();
		final int numberOfRows = universe.getNumberOfRows();

		if (column >= 0 && column < numberOfColumns && row >= 0
				&& row < numberOfRows) {
			cell = universe.getCellAt(row, column);
		}

		return cell;
	}

	/**
	 * Use double buffering.
	 * 
	 */
	@Override
	public void update(Graphics g) {

		Dimension d = getSize();

		if ((offScreenImage == null)) {
			offScreenImage = createImage(d.width, d.height);
		}

		paint(offScreenImage.getGraphics());

		g.drawImage(offScreenImage, 0, 0, null);
	}

	/**
	 * Draw this generation.
	 */
	@Override
	public void paint(Graphics g) {

		final int numberOfColumns = universe.getNumberOfColumns();
		final int numberOfRows = universe.getNumberOfRows();

		if (this.backgroundImage == null) {

			Dimension d = getSize();
			this.backgroundImage = createImage(d.width, d.height);

			Graphics backgroundImageGraphics = backgroundImage.getGraphics();

			// Draw background.
			backgroundImageGraphics.setColor(getBackground());
			backgroundImageGraphics.fillRect(0, 0, d.width, d.height);

			// Cell background
			backgroundImageGraphics.setColor(BACKGROUND_COLOR);
			backgroundImageGraphics.fillRect(0, 0, cellSize * numberOfColumns
					- 1, cellSize * numberOfRows - 1);

			// Draw grid
			backgroundImageGraphics.setColor(GRID_COLOR);
			for (int x = 1; x < numberOfColumns; x++) {
				backgroundImageGraphics.drawLine(x * cellSize - 1, 0, x
						* cellSize - 1, cellSize * numberOfRows - 1);
			}
			for (int y = 1; y < numberOfRows; y++) {
				backgroundImageGraphics.drawLine(0, y * cellSize - 1, cellSize
						* numberOfColumns - 1, y * cellSize - 1);
			}
		}

		g.drawImage(backgroundImage, 0, 0, null);

		// draw populated cells
		for (int row = 0; row < numberOfRows; row++) {

			for (int column = 0; column < numberOfColumns; column++) {

				Cell cell = universe.getCellAt(row, column);

				if (cell.getState() == CellState.ACTIVE) {
					g.drawImage(liveCellImage, column * cellSize, row
							* cellSize, this);
				}
			}
		}
	}

	/**
	 * Preferred size is number of rows and columns of the matrix times the size
	 * of the image.
	 */
	@Override
	public Dimension getPreferredSize() {
		final int numberOfColumns = universe.getNumberOfColumns();
		final int numberOfRows = universe.getNumberOfRows();

		return new Dimension(cellSize * numberOfColumns, cellSize
				* numberOfRows);
	}

	/**
	 * This is the minimum size (size of one cell).
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(cellSize, cellSize);
	}

}
