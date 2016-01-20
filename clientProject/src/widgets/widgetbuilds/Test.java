package widgets.widgetbuilds;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Test {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(736, 549);
		shell.setText("SWT Application");
		
		StatusList holder= new StatusList(shell, SWT.PUSH);
		holder.setBounds(0, 0, 247, 430);
		holder.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(holder.getSelection());
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		holder.AddItem("lior", false);
		holder.AddItem("eli", true);
		
		
		holder.pack();
		shell.pack();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
