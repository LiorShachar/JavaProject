package algorithms.mazeGenerators;

import java.io.DataOutputStream;import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.nio.ByteBuffer;
import algorithms.search.Searcher;

/**
 * 
 * 
 * <h1>Maze3d</h1> This class represents a 3d maze. a three dimensional int
 * matrix is used to represent the walls and paths of the maze (map) along with
 * methods to manipulate it or check its status.
 * <p>
 * <b>Notes:</b>
 *
 * @author Lior Shachar
 * @version 1.0
 * @since 2015-11-28
 */

public class Maze3d implements MazeProblem {

	private int map[][][];
	private int xSize;
	private int ySize;
	private int zSize;
	private Position StartPosition;
	private Position GoalPosition;

	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((GoalPosition == null) ? 0 : GoalPosition.hashCode());
		result = prime * result + ((StartPosition == null) ? 0 : StartPosition.hashCode());
		result = prime * result + Arrays.deepHashCode(map);
		result = prime * result + xSize;
		result = prime * result + ySize;
		result = prime * result + zSize;
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
		Maze3d other = (Maze3d) obj;
		if (GoalPosition == null) {
			if (other.GoalPosition != null)
				return false;
		} else if (!GoalPosition.equals(other.GoalPosition))
			return false;
		if (StartPosition == null) {
			if (other.StartPosition != null)
				return false;
		} else if (!StartPosition.equals(other.StartPosition))
			return false;
		if (!Arrays.deepEquals(map, other.map))
			return false;
		if (xSize != other.xSize)
			return false;
		if (ySize != other.ySize)
			return false;
		if (zSize != other.zSize)
			return false;
		return true;
	}



	public Maze3d(byte[] arr){
		ByteBuffer b = ByteBuffer.wrap(arr); // byte buffer allowes us to iterate over a byte array and get either an int or a single byte
		
		ySize=b.getInt();
		xSize=b.getInt();
		zSize=b.getInt();
		
		StartPosition= new Position(b.getInt(),b.getInt(),b.getInt()) ;
		GoalPosition= new Position(b.getInt(),b.getInt(),b.getInt()) ;
		
		this.map = new int[ySize][xSize][zSize]; // we init the maze with our int values
		
		// now we use a loop to read the map which is represented by byte values.
		
		for (int i = 0; i < ySize; i++) {

			for (int j = 0; j < xSize; j++) {

				for (int k = 0; k < zSize; k++) {

					this.map[i][j][k] = (int)b.get();
					

				}

			}
		}
		
		
		
	}

	

	/**
	 * return the maze data as a byte array, int's are represented as four bytes and their places in the array are fixed
	 * that way we are able to init the maze accordingly, and our data isn't limited to numbers below 127.
	 * **/
	public byte[] toByteArray() {
		int o;
		byte arr[] = new byte[(xSize * ySize * zSize) + 36]; // every position has 3 int's + 3 ints of x,y,z size. 9 int's in total equals 36 bytes with fixed indexes.
		byte temparr[];
		
		
		temparr = intToBytes(ySize); // init the array with the y size int
		for ( o = 0; o < 4; o++)
		{arr[o]=temparr[o];}
		
		temparr = intToBytes(xSize);// init the array with the x size int
		for ( o=0; o < 4; o++)
		{arr[4+o]=temparr[o];}
		
		temparr = intToBytes(zSize);// init the array with the z size int
		for ( o=0; o < 4; o++)
		{arr[8+o]=temparr[o];}

		temparr = intToBytes(this.getStartPosition().getY());// init the array with the start position y int
		for ( o=0; o < 4; o++)
		{arr[12+o]=temparr[o];}
		
		temparr = intToBytes(this.getStartPosition().getX());// init the array with the start position x int
		for ( o=0; o < 4; o++)
		{arr[16+o]=temparr[o];}
		
		temparr = intToBytes(this.getStartPosition().getZ());// init the array with the start position z int
		for ( o=0; o < 4; o++)
		{arr[20+o]=temparr[o];}
		
		temparr = intToBytes(this.getGoalPosition().getY());// init the array with the goal position y int
		for ( o=0; o < 4; o++)
		{arr[24+o]=temparr[o];}
		
		temparr = intToBytes(this.getGoalPosition().getX());// init the array with the goal position x int
		for ( o=0; o < 4; o++)
		{arr[28+o]=temparr[o];}
		
		temparr = intToBytes(this.getGoalPosition().getZ());// init the array with the goal position z int
		for ( o=0; o < 4; o++)
		{arr[32+o]=temparr[o];}
		
	

		int itr = 36; // maze map info starts from the 36th place on our array

		for (int i = 0; i < ySize; i++) {

			for (int j = 0; j < xSize; j++) {

				for (int k = 0; k < zSize; k++) {

					arr[itr] = (byte) this.map[i][j][k];
					itr++;

				}

			}
		}
		return arr;
	}

public static byte[] intToBytes(int i){
		
		ByteBuffer b = ByteBuffer.allocate(4);
		//b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
		b.putInt(i);
		return b.array();
		}
	
public static int bytesToInt(byte[] arr){
		
		ByteBuffer b = ByteBuffer.wrap(arr);
		return b.getInt();
		
		}
	
	
	public Maze3d(int ySize, int xSize, int zSize) { // C`tor, initialize every
														// cell with 1.

		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;

		this.map = new int[ySize][xSize][zSize];

		for (int i = 0; i < ySize; i++) {

			for (int j = 0; j < xSize; j++) {

				for (int k = 0; k < zSize; k++) {

					this.map[i][j][k] = 1;

				}

			}
		}
	}

	public int numOfMoves(Position p) {// number of moves available from the given position
		ArrayList<Position> list = getPossibleMovesList(p);
		return list.size();
	}

	public String[] getPossibleMoves(Position p) { //

		ArrayList<Position> moves = getPossibleMovesList(p);
		ArrayList<String> strings = new ArrayList<String>();
		for (Position move : moves)
			strings.add(move.toString());
		return strings.toArray(new String[strings.size()]);

	}

	public ArrayList<Position> getPossibleMovesList(Position p) { // a list of available Positions with the available coords
		ArrayList<Position> moves = new ArrayList<Position>();

		if (this.getCell(p.down()) == 0)
			moves.add(p.down());

		if (this.getCell(p.up()) == 0)
			moves.add(p.up());

		if (this.getCell(p.left()) == 0)
			moves.add(p.left());

		if (this.getCell(p.right()) == 0)
			moves.add(p.right());

		if (this.getCell(p.forward()) == 0)
			moves.add(p.forward());

		if (this.getCell(p.backward()) == 0)
			moves.add(p.backward());

		return moves;

	}

	
	public String[] getPossibleMovesStringDirections(Position p) {//get all possible moves as actual directions by strings

		String[] moves = new String[6];
		int c = 0;
		try {
			if (this.getCell(p.down()) == 0) {
				c++;
				moves[c] = "Down ";

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}

		try {
			if (this.getCell(p.up()) == 0) {

				c++;
				moves[c] = "Up ";

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}

		try {
			if (this.getCell(p.right()) == 0) {
				c++;
				moves[c] = "Right ";

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}

		try {
			if (this.getCell(p.left()) == 0) {
				c++;
				moves[c] = "Left ";

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}
		try {
			if (this.getCell(p.forward()) == 0) {
				c++;
				moves[c] = "Forward ";

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}
		try {
			if (this.getCell(p.backward()) == 0) {
				c++;
				moves[c] = "Backward ";
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bounds");
		}

		if (c == 0) {
			String[] newmoves = new String[1];
			newmoves[0] = "No moves available";
			return newmoves;
		} else {
			moves[0] = "Number of moves: " + (c);

			String[] newmoves = new String[c + 1];
			System.arraycopy(moves, 0, newmoves, 0, newmoves.length);
			return newmoves;
		}

	}

	public Position rndNode(Position p) { // returns a random neighbor node (a
											// closed cell 2 steps away from the
											// location)
		Random rnd = new Random();
		ArrayList<Position> nbrs = new ArrayList<Position>();
		if (this.getCell(p.up().up()) == 1) {
			nbrs.add(p.up().up());
		}

		if (this.getCell(p.down().down()) == 1) {
			nbrs.add(p.down().down());
		}

		if (this.getCell(p.right().right()) == 1) {
			nbrs.add(p.right().right());
		}

		if (this.getCell(p.left().left()) == 1) {
			nbrs.add(p.left().left());
		}

		if (this.getCell(p.forward().forward()) == 1) {
			nbrs.add(p.forward().forward());
		}

		if (this.getCell(p.backward().backward()) == 1) {
			nbrs.add(p.backward().backward());
		}
		if (nbrs.size() == 1)
			return nbrs.get(0);
		else if (nbrs.size() > 1)
			return nbrs.get(rnd.nextInt(nbrs.size() - 1));
		else
			return (new Position(-100, -100, -100));

	}

	public int[][] getCrossSectionByX(int cut) {

		int[][] temp = new int[this.ySize][this.zSize];
		for (int i = 0; i < this.ySize; i++) {
			for (int j = 0; j < this.zSize; j++) {
				try {
					temp[i][j] = map[i][cut][j];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
							"ERROR: Maze index out of bounds: cut (" + cut + ") row: (" + i + ") col: (" + j + ')');
					break;
				}

			}

		}
		return temp;

	}

	public int[][] getCrossSectionByY(int cut) {

		int[][] temp = new int[this.xSize][this.zSize];
		for (int i = 0; i < this.xSize; i++) {
			for (int j = 0; j < this.zSize; j++) {
				try {
					temp[i][j] = map[cut][i][j];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
							"ERROR: Maze index out of bounds: cut (" + cut + ") row: (" + i + ") col: (" + j + ')');
					break;
				}
			}

		}
		return temp;

	}

	public int[][] getCrossSectionByZ(int cut) {

		int[][] temp = new int[this.ySize][this.xSize];
		for (int i = 0; i < this.ySize; i++) {
			for (int j = 0; j < this.xSize; j++) {
				try {
					temp[i][j] = map[i][j][cut];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
							"ERROR: Maze index out of bounds: cut (" + cut + ") row: (" + i + ") col: (" + j + ')');
					break;
				}
			}

		}
		return temp;

	}

	public void wall(Position p) { // puts a wall in the position given
		try {
			if (p.getY() < ySize && p.getY() >= 0 && p.getX() < xSize && p.getX() >= 0 && p.getZ() < zSize
					&& p.getZ() >= 0)
				this.map[p.getY()][p.getX()][p.getZ()] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cant make a wall : out of bounds");

		}

	}

	public void path(Position p) { // puts a path in the position given
		try {
			if (p.getY() < ySize && p.getY() >= 0 && p.getX() < xSize && p.getX() >= 0 && p.getZ() < zSize
					&& p.getZ() >= 0)
				this.map[p.getY()][p.getX()][p.getZ()] = 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cant make a path : out of bounds");

		}

	}

	public int getCell(Position p) { // get the cell value in the position given
		try {
			return this.map[p.getY()][p.getX()][p.getZ()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}

	}

	public int getCell(int y, int x, int z) { // get the cell value in the
												// coords given

		try {
			return this.map[y][x][z];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cant get info : cell is out of bounds");
			return -1;
		}

	}

	public int[][][] getMap() {

		return map;
	}

	public void setMap(int[][][] map) {
		this.map = map;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public int getzSize() {
		return zSize;
	}

	public void setzSize(int zSize) {
		this.zSize = zSize;
	}

	public Position getGoalPosition() {
		return GoalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		GoalPosition = goalPosition;
	}

	public Position getStartPosition() {
		return StartPosition;
	}

	public void setStartPosition(Position startPosition) {

		StartPosition = startPosition;
	}
	/**
	 * generates a starting point in the boundaries of the maze.
	 */
	public void generateStartPosition() {

		int c;
		int w;
		int x;
		int y;
		int z;

		Random rnd = new Random();
		c = rnd.nextInt(2); // choose x y or z to be axis
		w = rnd.nextInt(1); // choose 0 or N

		switch (c) {
		case 0: // by X

			if (w == 1)
				w = this.xSize - 1;

			do {
				if (ySize > 1)
					y = rnd.nextInt(ySize - 1);
				else
					y = 0;
				if (zSize > 1)
					z = rnd.nextInt(zSize - 1);
				else
					z = 0;

			} while (this.numOfMoves(new Position(y, w, z)) != 1);

			this.StartPosition = new Position(y, w, z);
			this.path(this.StartPosition);
			break;

		case 1: // by Y

			if (w == 1)
				w = ySize - 1;
			do {
				if (xSize > 1)
					x = rnd.nextInt(xSize - 1);
				else
					x = 0;
				if (zSize > 1)
					z = rnd.nextInt(zSize - 1);
				else
					z = 0;

			} while (this.numOfMoves(new Position(w, x, z)) != 1);

			this.StartPosition = new Position(w, x, z);
			this.path(this.StartPosition);
			break;

		case 2: // by Z
			if (w == 1)
				w = zSize - 1;
			do {
				if (xSize > 1)
					x = rnd.nextInt(xSize - 1);
				else
					x = 0;
				if (ySize > 1)
					y = rnd.nextInt(ySize - 1);
				else
					y = 0;

			} while (this.numOfMoves(new Position(y, x, w)) != 1);

			this.StartPosition = new Position(y, x, w);
			this.path(this.StartPosition);
			break;

		default:

			if (w == 1)
				w = zSize - 1;
			do {
				if (xSize > 1)
					x = rnd.nextInt(xSize - 1);
				else
					x = 0;
				if (ySize > 1)
					y = rnd.nextInt(ySize - 1);
				else
					y = 0;

			} while (this.numOfMoves(new Position(y, x, w)) != 1);

			this.StartPosition = new Position(y, x, w);
			this.path(this.StartPosition);
			break;

		}

	}
/**
 * generates an exit point in the boundaries of the maze
 */
	public void generateGoalPosition() {

		int c;
		int w;
		int x;
		int y;
		int z;

		Random rnd = new Random();
		c = rnd.nextInt(2); // choose x y or z to be axis
		w = rnd.nextInt(1); // choose 0 or N

		switch (c) {
		case 0:

			if (w == 1)
				w = this.xSize - 1;

			do {
				if (ySize > 1)
					y = rnd.nextInt(ySize - 1);
				else
					y = 0;
				if (zSize > 1)
					z = rnd.nextInt(zSize - 1);
				else
					z = 0;

			} while (this.numOfMoves(new Position(y, w, z)) != 1 || (new Position(y, w, z).isNextTo(StartPosition))
					|| (new Position(y, w, z).equals(StartPosition)));

			this.GoalPosition = new Position(y, w, z);
			this.path(this.GoalPosition);
			break;

		case 1: // by Y

			if (w == 1)
				w = this.ySize - 1;
			do {
				if (xSize > 1)
					x = rnd.nextInt(xSize - 1);
				else
					x = 0;
				if (zSize > 1)
					z = rnd.nextInt(zSize - 1);
				else
					z = 0;
			} while (this.numOfMoves(new Position(w, x, z)) != 1 || (new Position(w, x, z).isNextTo(StartPosition))
					|| (new Position(w, x, z).equals(StartPosition)));

			this.GoalPosition = new Position(w, x, z);
			this.path(this.GoalPosition);
			break;

		case 2: // by Z
			if (w == 1)
				w = this.zSize - 1;
			do {
				if (xSize > 1)
					x = rnd.nextInt(xSize - 1);
				else
					x = 0;
				if (ySize > 1)
					y = rnd.nextInt(ySize - 1);
				else
					y = 0;

			} while ((this.numOfMoves(new Position(y, x, w)) != 1) || (new Position(y, x, w).isNextTo(StartPosition))
					|| (new Position(y, x, w).equals(StartPosition)));

			this.GoalPosition = new Position(y, x, w);
			this.path(this.GoalPosition);
			break;

		default:

			System.out.println("Random generated a faulty number");
			break;
		}

	}

}
