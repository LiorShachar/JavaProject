package algorithms.mazeGenerators;

import java.util.Random;


/**
 * 
 * 
* <h1>Common Maze Generator</h1>
* This is an abstract class represents a 3d maze generator.  
 * it has all the common features of all the 3d maze generators
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/




public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	
	public abstract Maze3d generate(int y,int x,int z); 
		
	
	public String measureAlgorithmTime(int sizey,int sizex,int sizez) {


		long x =System.currentTimeMillis();
		this.generate(sizey,sizex,sizez);
		long y =System.currentTimeMillis();
		String s = new String("Maze Generating time: "+(y-x)+"ms");
		return s;
	
	}
	public Position oddPosition(int y,int x,int z) // returns a random location with odd values
	{
		Random rnd = new Random();
		int rndy=0;
		int rndx=0;
		int rndz=0;
		
		
		while (rndy%2==0)
		{
			if(y==1)
				break;
			else
			rndy=rnd.nextInt(y);
		}
		while (rndx%2==0)
		{
			if(x==1)
				break;
			else
			rndx=rnd.nextInt(x);
		}
		while (rndz%2==0)
		{
			if(z==1)
				break;
			else
			rndz=rnd.nextInt(z);
		}
		return (new Position(rndy,rndx,rndz));
		
	}
}

