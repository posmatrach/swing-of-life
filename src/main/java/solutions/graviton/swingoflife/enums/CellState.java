package solutions.graviton.swingoflife.enums;

/**
 * Enum representing possible states of the cell (active and inactive)
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public enum CellState 
{
	ACTIVE {
		@Override
		public boolean isActive()
		{
			return true;
		}

		@Override
		public CellState toggle()
		{
			return CellState.INACTIVE;
		}
	},

	INACTIVE {
		@Override
		public boolean isActive()
		{
			return false;
		}

		@Override
		public CellState toggle()
		{
			return CellState.ACTIVE;
		}
	};

	public abstract boolean isActive();

	public abstract CellState toggle();
}
