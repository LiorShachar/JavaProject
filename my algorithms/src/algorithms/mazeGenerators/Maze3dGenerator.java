package algorithms.mazeGenerators;

/**
 * 
 * 
* <h1> Maze Generator interface</h1>
* every maze generator should implement this interface
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/



public interface Maze3dGenerator {
	public Maze3d generate(int y,int x,int z);
	public String measureAlgorithmTime(int y,int x,int z);
	

}
