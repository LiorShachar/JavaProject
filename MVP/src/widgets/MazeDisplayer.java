package widgets;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas{
	
	// just as a stub...
	int[][] mazeData={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
		};
	int levels=1; // the cross section axis index
	int curlvl=1;
	String name ="stub"; // name of the current maze
	
	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}
	
	public void setMazeName(String name){
		this.name=name;
	}
	public String getMazeName(){
		return name;
	}
	
	public abstract  void setCharacterPosition(int row,int col);
	public abstract  int getCharacterPositionX();
	public abstract  int getCharacterPositionY();

	public abstract void moveUp();
	
	public abstract void lvlUp();
	
	public abstract void lvlDown();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();
	
	public abstract  void setCharacter(GameCharacter chr);

	public abstract void setCurLvl(int l);
	public abstract int getCurLvl();
	public abstract void setLevels(int l);
	public abstract int getLevels();
}