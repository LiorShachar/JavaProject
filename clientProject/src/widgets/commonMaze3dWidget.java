package widgets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import controller.Command;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public abstract class commonMaze3dWidget extends Canvas {

	
	/**
	 * a common widget that makes a use with "my algorithms" library
	 * using the {@link Maze3d}  and {@link Position} classes
	 * */
	
	
	public commonMaze3dWidget(Composite parent, int style) {
		super(parent, style);
		
	}

	GameCharacter chr;
	Position currentPosition;
	Maze3d maze;
	String mazeName;
	WidCommand command;
	int[][] mazeData={
			{0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},
			{1,0,0,1,1,1,1,1,1,1,1,1,0,0,1},
			{1,1,0,0,1,1,1,1,1,1,1,0,0,1,1},
			{1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
			{1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
			{1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};

	
	public commonMaze3dWidget(Composite parent, int style, Maze3d maze, String name) {
		super(parent, style);
		
	}
	
	
	
	
	
	public GameCharacter getChr() {
		return chr;
	}





	public void setChr(GameCharacter chr) {
		this.chr = chr;
	}





	public WidCommand getCommand() {
		return command;
	}





	public void setCommand(WidCommand command) {
		this.command = command;
	}





	public int[][] getMazeData() {
		return mazeData;
	}




	
	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
		
	}









	public String getMazeName() {
		return mazeName;
	}





	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}





	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}

	public Maze3d getMaze() {
		return maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	abstract void moveCharacter(Position p); 
	
	public void setCharacter(GameCharacter chr) {
		this.chr = chr;

	}

	abstract void setCharacterPosition(Position p); 
	
	abstract int getCharacterPositionX(); 
	
	abstract int getCharacterPositionY();
	
	abstract int getCharacterPositionZ(); 

	abstract void moveForward();
	
	abstract void moveBackward(); 

	abstract void moveUp();

	abstract void moveDown();

	abstract void moveLeft();

	abstract void moveRight(); 

	
	

	

	


	
	
}
