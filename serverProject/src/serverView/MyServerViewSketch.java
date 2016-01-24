package serverView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MyServerViewSketch extends Shell {
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MyServerViewSketch shell = new MyServerViewSketch(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public MyServerViewSketch(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		List list_1 = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list_1.setForeground(SWTResourceManager.getColor(0, 153, 255));
		list_1.setBounds(10, 76, 223, 150);
		
		Button startBtn = new Button(this, SWT.NONE);
		
		startBtn.setBounds(10, 10, 75, 25);
		startBtn.setText("Run Server");
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 70, 434, 2);
		
		Button clrBtn = new Button(this, SWT.NONE);
		
		clrBtn.setBounds(10, 226, 75, 25);
		clrBtn.setText("clear");
		
		Label lblEventLog = new Label(this, SWT.NONE);
		lblEventLog.setBounds(10, 49, 55, 15);
		lblEventLog.setText("Event Log");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		table.setBounds(252, 76, 172, 150);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(56);
		tblclmnId.setText("ID");
		
		TableColumn tblclmnIpAddress = new TableColumn(table, SWT.NONE);
		tblclmnIpAddress.setWidth(112);
		tblclmnIpAddress.setText("IP Address");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {"number", "address"});
		tableItem.setText("item1");
		
		Button offBtn = new Button(this, SWT.NONE);
		
		offBtn.setBounds(336, 10, 75, 25);
		offBtn.setText("Shutdown");
		
		Button kickBtn = new Button(this, SWT.NONE);
		
		kickBtn.setBounds(336, 226, 75, 25);
		kickBtn.setText("kick");
		
		Label lblSessionLog = new Label(this, SWT.NONE);
		lblSessionLog.setBounds(239, 49, 75, 15);
		lblSessionLog.setText("Session log");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
