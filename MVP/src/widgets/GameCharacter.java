package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class GameCharacter extends Canvas{

	
		   int x,y,w,h;
		   
		   
		   
		   public GameCharacter(Composite parent, int style,int startx,int starty,int width,int height) {
			super(parent, style);
			x=startx;
			y=starty;
			w=width;
			h=height;
			// TODO Auto-generated constructor stub
			parent.addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					
					e.gc.setForeground(new Color(null,255,0,0));
					e.gc.drawOval(x*w,y*h, w, h);
					      
					}
			});
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

	
	

