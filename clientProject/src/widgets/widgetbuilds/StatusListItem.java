package widgets.widgetbuilds;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;

public class StatusListItem extends Composite {

	/**
	 * a widget that shows a label and a radio button for status
	 */
	
	
	
	
	Button Namelabel;
	Button statusBtn;
	
	
	
	
	
	public StatusListItem(Composite parent, int style ,String name, boolean status) {
		
		super(parent, style);
		
	
		GridLayout gridLayout = new GridLayout(2, true);
		setLayout(gridLayout);
		Namelabel = new Button(this, SWT.NONE);
		Namelabel.setText(name);
		statusBtn = new Button(this, SWT.RADIO);
		statusBtn.setSelection(status);
		statusBtn.setEnabled(status);

	}

	
	
	
	
	
	public boolean Status() {
		return statusBtn.getSelection();
	}






	public void setStatus(boolean status) {
		statusBtn.setSelection(status);
		statusBtn.setEnabled(status);
	}






	public String getName() {
		return this.Namelabel.getText();
	}






	public void setName(String name) {
		this.Namelabel.setText(name);
	}






	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
