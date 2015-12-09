package algorithms.mazeGenerators;

import java.util.Random;
/**
 * 
 * 
* <h1>Simple Maze Generator</h1>
* this class has an improvised maze generating algorithm.<p>
*  a random maze is created by random paths, then it builds walls\limits on the maze boundaries.<p>
*  the start and exit are then generated and a special method builds a path between them.
*  
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {



	
public void pathMaker (Maze3d maze , Position s , Position e)
{
	maze.path(s);
	maze.path(e);
	int rmoves, fmoves, umoves;
	rmoves =e.getX()-s.getX();
	fmoves = e.getZ()-s.getZ();
	umoves = e.getY()-s.getY();
	Position current =s;
	if (rmoves>0)
		for(int i=0;i<rmoves;i++){
			current=current.right();
			maze.path(current);
		}
	if (rmoves<0)
		for(int i=0;i<rmoves*-1;i++){
			current=current.left();
			maze.path(current);
		}
	if (fmoves>0)
		for(int i=0;i<fmoves;i++){
			current=current.forward();
			maze.path(current);
		}
	if (fmoves<0)
		for(int i=0;i<fmoves*-1;i++){
			current=current.backward();
			maze.path(current);
		}
	if (fmoves>0)
		for(int i=0;i<umoves;i++){
			current=current.up();
			maze.path(current);
		}
	if (fmoves<0)
		for(int i=0;i<umoves*-1;i++){
			current=current.down();
			maze.path(current);
		}
		
	
	
	
	
	
	
	
	

}



	public Maze3d generate(int y, int x, int z) {
		
		if(y%2==0)
			y++;
		if(x%2==0)
			x++;
		if(z%2==0)
			z++;
		Maze3d temp = new Maze3d(y,x,z);
		Random rnd=new Random();
		int vary=-1;
		int varx=-1;
		int varz=-1;
		int limit = (y*x*z/2)+1 ; // we want to limit the number of cells we want to convert to path
		int visited =0;
		while (visited < limit)
		{
		if(y==1)
			vary=0;
		else
			vary=rnd.nextInt(y-2)+1;
			
					if(x==1)
						varx=0;
					else
						varx=rnd.nextInt(x-2)+1;
						
								if(z==1)
									varz=0;
								else
									varz=rnd.nextInt(z-2)+1;
		
								temp.path(new Position(vary,varx,varz));
								visited++;
		
			
			
		}
		temp.generateStartPosition();
		temp.generateGoalPosition();
		this.pathMaker(temp, temp.getStartPosition(), temp.getGoalPosition());
		
		
		
		
		return temp;
		
		
	}

}


