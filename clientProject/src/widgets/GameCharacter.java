package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public abstract class GameCharacter extends Canvas{

	
		   int x=0; // x represent vertical view by default
		   int z=0; // z represent horizontal view by default
		   int y=0; // y represent a LEVEL by default
		   
		   int w=0; //char width size 
		   int h=0;//char height size 
		   
		   
		   
		  



		public void setZ(int z) {
			this.z = z;
		}



		public GameCharacter(Composite parent, int style) {
			super(parent, style);
			
		
		}

		   
		public abstract void paint(PaintEvent e,int col,int row,int width, int height);   
		public abstract void paint(PaintEvent e);
		   
		 public int getZ() {
				return z;
			}


		public int getW() {
			return w;
		}


		public void setW(int w) {
			this.w = w;
		}


		public int getH() {
			return h;
		}


		public void setH(int h) {
			this.h = h;
		}


		public int getX() {
			return x;
		}


		public void setX(int x) {
			this.x = x;
		}


		public int getY() {
			return y;
		}


		public void setY(int y) {
			this.y = y;
		}
		   
		   
		}

	
	

