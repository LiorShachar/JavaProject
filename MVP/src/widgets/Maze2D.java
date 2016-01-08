package widgets;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Maze2D extends MazeDisplayer{
	
	
	GameCharacter chr;
	


	 public Maze2D(Composite parent,int style){
	        super(parent, style);
	        //TEST
	        chr= new GameCharacter(0,2);
	        
	    	// set a white background   (red, green, blue)
	    	setBackground(new Color(null, 255, 255, 255));
	    	addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					   e.gc.setForeground(new Color(null,0,0,0));
					   e.gc.setBackground(new Color(null,0,0,0));

					   int width=getSize().x;
					   int height=getSize().y;

					   int w=width/mazeData[0].length;
					   int h=height/mazeData.length;

					   for(int i=0;i<mazeData.length;i++)
					      for(int j=0;j<mazeData[i].length;j++){
					          int x=j*w;
					          int y=i*h;
					          if(mazeData[i][j]!=0)
					              e.gc.fillRectangle(x,y,w,h);
					          
					          
					          chr.paint(e,w, h);
					      }
					}
			});
	 }


	@Override
	public void setCharacterPosition(int row, int col) {
		if(row>=0 && row<mazeData[0].length && col>=0 && col<mazeData.length && mazeData[row][col]==0){
		this.chr.setX(col);
		this.chr.setY(row);
		}
	}

	@Override
	public void moveUp() {
		
		setCharacterPosition(chr.getY()-1, chr.getX());
	}

	@Override
	public void moveDown() {
		
		setCharacterPosition(chr.getY()+1, chr.getX());
	}

	@Override
	public void moveLeft() {
		
		setCharacterPosition(chr.getY(), chr.getX()-1);
	}

	@Override
	public void moveRight() {
		
		setCharacterPosition(chr.getY(), chr.getX()+1);
	}


	private void moveCharacter(int x,int y){
		
			getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {
					redraw();
				}
			});
		}
	
	
}
