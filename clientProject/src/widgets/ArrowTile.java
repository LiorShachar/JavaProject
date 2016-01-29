package widgets;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;


/**
 * a custom widget for indicating if a move is available (usually with an image of an arrow or direction)
 * once the move isn't valid the image will be still drawn but with low opacity (alpha set to 100)
 * once the move is valid the image will be painted on full opacity.
 * */


public class ArrowTile extends Canvas {

	Image arrow;
	
	boolean valid;
	
	
	public ArrowTile(Composite parent, int style, Image arrow) {
		super(parent, SWT.NO_BACKGROUND);
		this.arrow = arrow;
		
		
		
	}

	public void paint(PaintEvent e, int width, int height,int x,int y) {
		
		
		e.gc.setAlpha(255);
		if(!valid)
			e.gc.setAlpha(100);
			
		e.gc.drawImage(arrow, 0, 0, arrow.getBounds().width, arrow.getBounds().height, x, y, width, height);
		
		
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
	
	
}
