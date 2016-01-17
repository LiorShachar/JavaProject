package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ImageGameCharacter extends Canvas{

			Image image;
	
		   int x=0; // x represent vertical view by default
		   int z=0; // z represent horizontal view by default
		   int y=0; // y represent a LEVEL by default
		   int w=0; //char width size 
		   int h=0;//char height size 
		   
		   
		   
		   public int getZ() {
			return z;
		}



		public void setZ(int z) {
			this.z = z;
		}



		public ImageGameCharacter(Composite parent, int style,Image image) {
			super(parent, style);
			this.image= image;
			
			
		}

		   
		   
		   public void paint(PaintEvent e,int z,int x,int w, int h){
			
			   e.gc.drawImage(image, 0, 0, 556,768,z, x, (int)Math.round(w*1.2), (int)Math.round(h*1.3));
		        
			   
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

	
	

