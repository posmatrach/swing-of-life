package solutions.graviton.swingoflife;

public enum CellEnvironment 
{ 
	NORTHWEST(-1, -1),
	NORTH(0, -1),
	NORTHEAST(1, -1),
	EAST(1, 0),
	SOUTHEAST(1, 1), 
	SOUTH(0, 1), 
	SOUTHWEST(-1, 1),
	WEST(-1, 0);
	
	
	private int offsetX;
	
	private int offsetY;
	
	private CellEnvironment(int offsetX, int offsetY)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public Coordinates getAbsoluteCoordinates(Coordinates other)
	{
		return new Coordinates(other.getX() + offsetX, other.getY() + offsetY);
	}
}
