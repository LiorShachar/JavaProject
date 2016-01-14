package algorithms.mazeGenerators;

/**
 * 
 * 
 * <h1>Position</h1>
 *  a class which represent a state with 3 coordinates in a 3 dimensional world.
 *  it has methods that help with actions and info about relative Positions
 * <p>
 * <b>Notes: </b><p> Y - height/level (2d cut) (up/down) <p>X - Rows (left/right) <p>Z -
 * columns (forwards/backwards)
 * 
 * @author Lior Shachar
 * @version 1.0
 * @since 2015-11-28
 */

public class Position {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	private int x;
	private int y;
	private int z;

	public Position(int y, int x, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position(Position p) {
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
	}

	
	/**
	 * 
	 * @return upper position by +1
	 */
	
	public Position up() {
		Position p = new Position(y + 1, x, z);
		return p;
	}
	/**
	 * 
	 * @return the position below 
	 */
	public Position down() {
		Position p = new Position(y - 1, x, z);
		return p;
	}
	/**
	 * 
	 * @return the position to the right
	 */
	public Position right() {
		Position p = new Position(y, x + 1, z);
		return p;
	}
	/**
	 * 
	 * @return the position to the left
	 */
	public Position left() {
		Position p = new Position(y, x - 1, z);
		return p;
	}
	/**
	 * 
	 * @return the forward position by +1
	 */
	public Position forward() {
		Position p = new Position(y, x, z + 1);
		return p;
	}
	/**
	 * 
	 * @return backward position by -1
	 */
	public Position backward() {
		Position p = new Position(y, x, z - 1);
		return p;
	}

	
	//an older, irrelevant version of equals
	public Boolean isTheSameAs(Position p) {

		if ((y == p.getY()) && (x == p.getX()) && (z == p.getZ()))
			return true;
		else
			return false;
	}

	
	/**
	 * 
	 * @return if p is in a relative position to this position
	 */
	public Boolean isNextTo(Position p) {
		if (p.isTheSameAs(this.up()) || p.isTheSameAs(this.down()) || p.isTheSameAs(this.forward())
				|| p.isTheSameAs(this.backward()) || p.isTheSameAs(this.left()) || p.isTheSameAs(this.right()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {

		return ("{"+"Y:" + this.y + ","+"X:" + this.x + ","+"Z:" + this.z + "}");
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
