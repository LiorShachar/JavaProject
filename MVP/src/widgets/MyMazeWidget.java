package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MyMazeWidget extends MazeDisplayer {
	
	ImageGameCharacter chr;
	Position currentPosition;
	Maze3d maze;
	String mazeName;
	boolean won=false;
	Color colors[]={new Color(null, 0,0, 255),new Color(null, 0,0, 255)};
	
	
	
	
	public Position getCurrentPosition() {
		return currentPosition;
	}




	public String getMazeName() {
		return mazeName;
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




	public MyMazeWidget(Composite parent, int style, Maze3d maze,String name) {
		super(parent, style);
		chr= new ImageGameCharacter(this,SWT.NONE,new Image(getDisplay(),"C:/Users/lior/Desktop/Chibi_Goku_2.png"));
		currentPosition= new Position(0,0,0);
		this.maze=maze;
		this.mazeName=name;
		set3DCharacterPosition(maze.getStartPosition());
		setBackground(new Color(null, 255, 255, 255));
		addDisposeListener(new DisposeListener() {
			
			

			@Override
			public void widgetDisposed(DisposeEvent e) {
				chr.dispose();
				
			}
		});
		
		
		
    	addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				
					e.gc.setForeground(new Color(null,0,100,0));
				   e.gc.setBackground(new Color(null,0,0,100));

				   int width=getSize().x;
				   int height=getSize().y;
				   
				   int mx=width/2;

				   double w=(double)width/mazeData[0].length;
				   double h=(double)height/mazeData.length;

				   for(int i=0;i<mazeData.length;i++){
					   double w0=0.7*w +0.3*w*i/mazeData.length;
					   double w1=0.7*w +0.3*w*(i+1)/mazeData.length;
					   double start=mx-w0*mazeData[i].length/2;
					   double start1=mx-w1*mazeData[i].length/2;
				      for(int j=0;j<mazeData[i].length;j++){
				          double []dpoints={start+j*w0,i*h,start+j*w0+w0,i*h,start1+j*w1+w1,i*h+h,start1+j*w1,i*h+h};
				          double cheight=h/2;
				          if(mazeData[i][j]!=0)
				        	  paintCube(dpoints, cheight,e);
				          
				          if(i==chr.x && j==chr.z){
				        	  
				        	
					         chr.paint(e, (int)Math.round(dpoints[0]),(int)Math.round(dpoints[1]-cheight/2),(int)Math.round((w0+w1)/2),(int)Math.round(h));
							 
							  				        	  
				          }
				          
					        
				          
				      }
				      
				  
				   e.gc.setForeground(new Color(null,255,0,0));
				   e.gc.drawString("Position: "+currentPosition, 0,20);
				   if (won)
					   e.gc.drawString(" YOU WON !!!!!!! ", width/2,height/2);
				   
				}
			}
		});
	}

	
	
	
	/**
	 * 
	 * moves the character to the position given only if the maze data allow it (boundries ,walls etc)
	 * **/
	public void moveCharacter(int level ,int row, int col){
		if(maze.getCell(new Position(level,row,col))==0){
			set3DCharacterPosition(level,row,col);
			
				
			
		}
		
		
		
	}
	
	public void moveCharacter(Position p){
		moveCharacter(p.getY() ,p.getX(), p.getZ());
		getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				redraw();
			}
		});
		}
	
	
	public void updateMazeData() {
		setMazeData(maze.getCrossSectionByY(currentPosition.getY()));
		getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				redraw();
			}
		});

		
	}
	
	
	public void set3DCharacterPosition(int level ,int row, int col) {
		chr.setY(level);
		chr.setX(row);
		chr.setZ(col);
		currentPosition.setY(level);
		currentPosition.setX(row);
		currentPosition.setZ(col);
		updateMazeData();
		getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				redraw();
			}
		});
		if(currentPosition.equals(maze.getGoalPosition()))
			won=true;
		else
			won=false;
			
	}
	
	public void set3DCharacterPosition(Position p) {
		set3DCharacterPosition(p.getY(),p.getX(),p.getZ());
		

	}

	@Override
	public void set2DCharacterPosition(int row, int col) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCharacterPositionX() {
		return currentPosition.getX();
	}

	@Override
	public int getCharacterPositionY() {
		return currentPosition.getY();
	}

	
	public void moveForward() {
		moveCharacter(currentPosition.forward());

	}

	
	public void moveUp() {
		moveCharacter(currentPosition.up());

	}

	
	public void moveDown() {
		moveCharacter(currentPosition.down());

	}

	
	public void moveBackward() {
		moveCharacter(currentPosition.backward());

	}

	@Override
	public void moveLeft() {
		moveCharacter(currentPosition.left());
	}

	@Override
	public void moveRight() {
		moveCharacter(currentPosition.right());

	}

	@Override
	public void setCharacter(ImageGameCharacter chr) {
		this.chr=chr;

	}


	private void paintCube(double[] p,double h,PaintEvent e){
        int[] f=new int[p.length];//FLOOR
        for(int k=0;k<f.length;f[k]=(int)Math.round(p[k]),k++);
        
        
        
        int[] r=f.clone();//ROOF
        for(int k=1;k<r.length;r[k]=f[k]-(int)(h),k+=2);
        

        int[] b={r[0],r[1],r[2],r[3],f[2],f[3],f[0],f[1]};
        int[] fr={r[6],r[7],r[4],r[5],f[4],f[5],f[6],f[7]};
        int[] right={r[2],r[3],f[2],f[3],f[4],f[5],r[4],r[5]};
        int[] left={r[0],r[1],f[0],f[1],f[6],f[7],r[6],r[7]};
        
        
        
        e.gc.setBackground(new Color(null, 0, 170, 255));
        e.gc.fillPolygon(right);
        e.gc.fillPolygon(left);
        e.gc.fillPolygon(fr);
        
        
        e.gc.setForeground(new Color(null, 255, 255, 255));
        e.gc.drawPolygon(right);
        e.gc.drawPolygon(left);
        e.gc.drawPolygon(fr);
        
        
        e.gc.setBackground(new Color(null, 180, 230, 255));
        e.gc.fillPolygon(r);
        e.gc.setForeground(new Color(null, 255, 255, 255));
        e.gc.drawPolygon(r);
		
	}



	
}
