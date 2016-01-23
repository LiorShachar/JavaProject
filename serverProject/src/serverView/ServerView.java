package serverView;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

public class ServerView extends commonGuiView {

	private Table table;
	
	List eventList;
	
	Button startBtn ;
	
	Label label ;
	
	Button clrBtn ;
	
	Label lblEventLog ;
	
	
	
	TableColumn tblclmnId ;
	
	TableColumn tblclmnIpAddress ;
	
	Button offBtn ;
	
	Button kickBtn;
	
	Label lblSessionLog;
	
	HashMap<String, Object> notifications;
	
	
	
	public ServerView(String title, int width, int height) {
		super(title, width, height);
		// TODO Auto-generated constructor stub
		notifications = new HashMap<String, Object>();
	}

	
	public void start() {
		
		run();
		
	}
	
	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);

	}
	
	@Override
	void initWidgets() {
		
		 eventList = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		eventList.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		eventList.setBounds(10, 76, 223, 150);
		
		 startBtn = new Button(shell, SWT.NONE);
		startBtn.setBounds(10, 10, 75, 25);
		startBtn.setText("Run Server");
		
		 label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 70, 434, 2);
		
		 clrBtn = new Button(shell, SWT.NONE);
		clrBtn.setBounds(10, 226, 75, 25);
		clrBtn.setText("clear");
		
		 lblEventLog = new Label(shell, SWT.NONE);
		lblEventLog.setBounds(10, 49, 55, 15);
		lblEventLog.setText("Event Log");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		table.setBounds(239, 76, 172, 150);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		 tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(29);
		tblclmnId.setText("ID");
		
		 tblclmnIpAddress = new TableColumn(table, SWT.NONE);
		tblclmnIpAddress.setWidth(79);
		tblclmnIpAddress.setText("IP Address");
		
		 offBtn = new Button(shell, SWT.NONE);
		offBtn.setBounds(336, 10, 75, 25);
		offBtn.setText("Shutdown");
		
		 kickBtn = new Button(shell, SWT.NONE);
		kickBtn.setBounds(336, 226, 75, 25);
		kickBtn.setText("kick");
		
		 lblSessionLog = new Label(shell, SWT.NONE);
		lblSessionLog.setBounds(239, 49, 75, 15);
		lblSessionLog.setText("Session log");
		initListeners();

	}
	
	void initListeners(){
		startBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scno("Start","");
			}
		});
		
		clrBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventList.removeAll();
				
			}
		});
		
		offBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scno("Close","");
			}
		});
		
		kickBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scno("Kick","");
			}
		});
		
		
	}

	
	public Object getData(String string) {
		return notifications.get(string);
	}
	
}
