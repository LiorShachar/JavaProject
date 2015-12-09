
package algorithms.mazeGenerators;
import java.util.Stack;
import java.util.EmptyStackException;


/**
 * 
 * 
* <h1>My Maze Generator</h1>
* this class uses a DFS based generating algorithm.<p>
*  
* 
*  
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/


public class MyMaze3dGenerator extends CommonMaze3dGenerator {


	
	
	public Maze3d generate(int y,int x,int z) {

		
		if(y%2==0)
			y++;
		if(x%2==0)
			x++;
		if(z%2==0)
			z++;
		
	  // using the DFS algorithm with a stack
		Maze3d temp = new Maze3d(y,x,z);
		int total = x*y*z;
		Position current = this.oddPosition(y, x, z);
		int visited=0;
		try{
		temp.path(current);
		visited=1;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{System.out.println("current cell generated is out of bounds");}
		Stack<Position> pstack = new Stack<Position>();
		
		
		while ( visited<total)
		{
			Position potential = temp.rndNode(current);// rndNode will return a random neighbor the DFS can visit
			if( potential.getX() >= 0) // if there are no neighbors potential will returned as (-100,-100,-100) its enough to check only one of the values
			{
				
				if(potential.getX()>current.getX()) // if the neighbor is to the right
					{temp.path(potential);       //create the path inside the neighbor position
					temp.path(current.right()); // create a path between current and neighbor
					}
				else if(potential.getX()<current.getX()) // if the neighbor is to the left
				{temp.path(potential);       //create the path inside the neighbor position
				temp.path(current.left()); // create a path between current and neighbor
				}
				else if(potential.getZ()>current.getZ()) // if the neighbor is forward
				{temp.path(potential);       //create the path inside the neighbor position
				temp.path(current.forward()); // create a path between current and neighbor
				}
				else if(potential.getZ()<current.getZ()) // if the neighbor is backwards
				{temp.path(potential);       //create the path inside the neighbor position
				temp.path(current.backward()); // create a path between current and neighbor
				}
				else if(potential.getY()>current.getY()) // if the neighbor is above
				{temp.path(potential);       //create the path inside the neighbor position
				temp.path(current.up()); // create a path between current and neighbor
				}
				else if(potential.getY()<current.getY()) // if the neighbor is below
				{temp.path(potential);       //create the path inside the neighbor position
				temp.path(current.down()); // create a path between current and neighbor
				}
				else
				{
					System.out.println("ERROR: Can't find relative position of the potential neighbor ");
				}
				
					pstack.push(current);
					current= potential;
					visited++;
					
				}
			else{
				try{
				current=pstack.pop();
				visited++;
				}
				catch(EmptyStackException e){
					
					temp.generateStartPosition();
					temp.generateGoalPosition();
					return temp;
					
				}
				}
			
				
					
					
		}
		
		return temp;
		
		
		
		
		
	}
	
	

	
	

	

	
	

}




