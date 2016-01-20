package widgets.widgetbuilds;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.layout.GridLayout;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CLabel;

public class StatusList extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	CopyOnWriteArrayList<StatusListItem> items;   // THREAD SAFE ARRAYLIST !
	String selected;
	Label label;
	Label lblSelectedlabel;
	
	public StatusList(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Selected Maze:");
		
		lblSelectedlabel = new Label(this, SWT.NONE);
		lblSelectedlabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		items = new CopyOnWriteArrayList<StatusListItem>();
		

	}

	public void AddItem(String name, boolean status) {
		StatusListItem s= new StatusListItem(this, SWT.PUSH, name, status);
		s.Namelabel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
			selected=s.getName();
			lblSelectedlabel.setText(selected);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		items.add(s );
		
	}
	
	
	public void setItem(int index , String name, boolean status) {
		
		StatusListItem m=items.get(index);
		m.setName(name);
		m.setStatus(status);
		

		}
	
	
	public String getSelection() {
		return lblSelectedlabel.getText();

		}
	
	
	
	public void RemoveItem(int index) {
		
	StatusListItem m=items.get(index);
	items.remove(index);
	m.dispose();
	
	}
	
	public void RemoveAll() {
		
		for(StatusListItem s : items)
			RemoveItem(items.indexOf(s));
		
		

		}
	
	
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
