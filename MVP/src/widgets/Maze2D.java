package widgets;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Maze2D extends MazeDisplayer{
	
	
	GameCharacter chr;
	


	 public Maze2D(Composite parent,int style){
	        super(parent, style);
	        //TEST
	        
	        
	    	// set a white background   (red, green, blue)
	    	setBackground(new Color(null, 255, 255, 255));
	    	addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					
					
					   e.gc.setForeground(new Color(null,0,0,0));
					   e.gc.setBackground(new Color(null,0,0,0));

					   e.gc.drawString("Level: "+getCurLvl(), 0,0);
					   
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
					          
					          
					          
					         
						        
					          
					      }
					   chr.setW(w);
					   chr.setH(h);
					   e.gc.setForeground(new Color(null,0,0,255));
					   e.gc.drawString("Level: "+getCurLvl(), 0,0);
					   e.gc.drawString("Position: "+getCharacterPositionX()+","+getCharacterPositionY(), 0,20);
					   
					}
			});
	 }


	 
	 
	 
	public GameCharacter getChr() {
		return chr;
		
	}











	@Override
	public void setCharacterPosition(int row, int col) { ////// we opposite the direction because the user see the maze differently
		this.chr.setX(col);
		this.chr.setY(row);
		redraw();
		
	}

	@Override
	public void moveUp() {
		
		moveCharacter( chr.getX(),chr.getY()-1);
	}

	@Override
	public void moveDown() {
		
		moveCharacter(chr.getX(),chr.getY()+1 );
	}

	@Override
	public void moveLeft() {
		
		moveCharacter(chr.getX()-1,chr.getY());
	}

	@Override
	public void moveRight() {
		
		moveCharacter(chr.getX()+1,chr.getY() );
	}


	private void moveCharacter(int x,int y){
		if(x>=0 && x<mazeData[0].length && y>=0 && y<mazeData.length && mazeData[y][x]==0){
			this.chr.setX(x);
			this.chr.setY(y);
			redraw();
			/*
			getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {
					redraw();
					
				}
			});
			*/
				
			
		}
	
	
}





	@Override
	public void setCharacter(GameCharacter chr) {
		this.chr = chr;
		int width=getSize().x;
		int height=getSize().y;

		int w=width/mazeData[0].length;
		int h=height/mazeData.length;
		
		this.chr.setW(w);
		this.chr.setH(h);
		
		
	}





	@Override
	public void lvlUp() {
		curlvl++;
		redraw();
	}





	@Override
	public void lvlDown() {
		curlvl--;
		redraw();
	}





	@Override
	public void setLevels(int l) {
		this.levels=l;
		
	}





	@Override
	public void setCurLvl(int l) {
		this.curlvl=l;
		redraw();
		
	}





	@Override
	public int getCharacterPositionX() {
		return chr.getX();
	}





	@Override
	public int getCharacterPositionY() {
		return chr.getY();
	}





	@Override
	public int getCurLvl() {
		
		return this.curlvl;
	}





	@Override
	public int getLevels() {
		
		return this.levels;
	}
}