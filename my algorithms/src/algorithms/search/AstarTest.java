package algorithms.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;

public class AstarTest {
	
	
	
	Heuristic h = new MazeAirDis();
	Astar<Position> star = new Astar<Position>(h);
	MyMaze3dGenerator generator = new MyMaze3dGenerator();
	Maze3d maze = generator.generate(100, 100, 100);
	searchableMaze3d tested = new searchableMaze3d(maze);
	
	
	
	

	@Test
	public void backtraceshouldreturnnull() {

		
		 assertEquals("no parent states should return null", null,star.backTrace(new State<Position>(new Position(0,0,0)), new State<Position>(new Position(0,5,7))) ); 
		 assertEquals("right argument is null should return null", null,star.backTrace(new State<Position>(new Position(0,0,0)), null ) ); 
		 assertEquals("no params should return null", null,star.backTrace(null, null ) ); 
		 assertEquals("right argument is null should return null", null,star.backTrace(null, new State<Position>(new Position(0,0,0)) ) ); 
	   
	}

	
	@Test (expected = Exception.class)
	public void nullgoalsearchshouldreturnexception() {

		 maze.setGoalPosition(null);
		 tested = new searchableMaze3d(maze);
		 star.search(tested) ; 
		 
	}
	
	@Test (expected = Exception.class)
	public void startsearchshouldreturnnull() {

		 maze.setStartPosition(null);
		 tested = new searchableMaze3d(maze);
		star.search(tested) ; 
		 
	}
	

	@Test (expected = Exception.class)
	public void nullparamshouldreturnnull() {

		 maze.setGoalPosition(null);
		 tested = new searchableMaze3d(maze);
		 star.search(tested) ; 
		 
	}
	
	@Test (expected = Exception.class)
	public void nullparametersearchshouldreturnnull() {

		
		star.search(null) ; 
		 
	}
	
	
	
	
}
