package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.MazeAirDis;
import algorithms.search.MazeManDis;
import algorithms.search.Solution;
import algorithms.search.searchableMaze3d;

public class Demo {

	
public	void run(){
		MyMaze3dGenerator creator = new MyMaze3dGenerator();
		Maze3d maze = creator.generate(12 ,13, 15);
		printMaze(maze.getCrossSectionByY(0));
		
		searchableMaze3d sermaze = new searchableMaze3d(maze);
		BFS<Position> solveBfs= new BFS<Position>();
		Astar<Position> solveMan= new Astar<Position>(new MazeManDis());
		Astar<Position> solveAir= new Astar<Position>(new MazeAirDis());
		
		
		
		
		Solution<Position> solution = solveBfs.search(new searchableMaze3d(maze));
		
		System.out.println("Starting point: " +sermaze.getInitialState());
		System.out.println("Exit point: " +sermaze.getGoalState());
		System.out.println("evaluated nodes in simple BFS: "+solveBfs.getNumberOfNodesEvaluated());
		solution.printSolution();
		System.out.println("*****************************************************************************");
		 solution = solveMan.search(new searchableMaze3d(maze));
		System.out.println("evaluated nodes in manhatten distance astar: "+solveMan.getNumberOfNodesEvaluated());
		solution.printSolution();
		System.out.println("*****************************************************************************");
		 solution = solveAir.search(new searchableMaze3d(maze));
		System.out.println("evaluated nodes in air distance astar: "+solveAir.getNumberOfNodesEvaluated());
		solution.printSolution();
		
		
		
		
		
		
	}
	
	public static void printMaze(int[][] maze) {   //prints a 2d maze matrix
		for (int i=0; i < maze.length ; i++){
			
			for (int j=0 ; j < maze[0].length; j++){ 
				System.out.print(maze[i][j]);
			}
			System.out.print("\n");
		}
		
}

}
	
