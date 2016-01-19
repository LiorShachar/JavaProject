package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ImageGameCharacter extends GameCharacter{

			Image image;
	
	
		   
		



		public ImageGameCharacter(Composite parent, int style,Image image) {
			super(parent, style);
			this.image= image;
			
			
		}

		   
		   
		   public void paint(PaintEvent e,int col,int row,int width, int height){
			
			   e.gc.drawImage(image, 0, 0, 556,768,col, row, (int)Math.round(width*1.2), (int)Math.round(height*1.3));
		        
			   
			}
		   
		



		@Override
		public void paint(PaintEvent e) {
			// TODO Auto-generated method stub
			
		}
		   
		   
		}

	
	

