package widgets;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

public class GameCharacter {

	
		   int x,y;
		   public GameCharacter(int x,int y) {
			this.x=x;this.y=y;
		   }
		   
		   
		   public void paint(PaintEvent e,int w,int h){
			e.gc.setForeground(new Color(null,255,0,0));
			e.gc.drawOval(x*w,y*h, w, h);
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

	
	

