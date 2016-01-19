package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;

public class PropertiesWidget extends Shell {

	/***
	 * 
	 * this class is a part of the GUI view
	 * 
	 * */
	
	
	
	public PropertiesWidget(Display display) {
		
		super(display, SWT.SHELL_TRIM);
		setSize(247, 400);
		setLayout(new GridLayout(3, false));
		setText("Properties");
		
		
		Label lblSettings = new Label(this, SWT.NONE);
		lblSettings.setText("Settings");
		lblSettings.setFont(SWTResourceManager.getFont("Consolas", 14, SWT.BOLD));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label uiLabel = new Label(this, SWT.NONE);
		uiLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		uiLabel.setText("User Interface");
		new Label(this, SWT.NONE);

		Combo uiCombo = new Combo(this, SWT.NONE);
		uiCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		uiCombo.setItems(new String[] { "GUI", "CLI" });
		new Label(this, SWT.NONE);

		Label genLabel = new Label(this, SWT.NONE);
		genLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		genLabel.setText("Maze generating algorithm");
		new Label(this, SWT.NONE);

		Combo genCombo = new Combo(this, SWT.NONE);
		genCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(this, SWT.NONE);

		Label solveLabel = new Label(this, SWT.NONE);
		solveLabel.setText("Maze solving algorithm");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Combo solveCombo = new Combo(this, SWT.NONE);
		solveCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(this, SWT.NONE);

		Combo heurCombo = new Combo(this, SWT.NONE);
		heurCombo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		heurCombo.setItems(new String[] { "Manhatten", "AirDistance" });
		heurCombo.setText("Astar Heuristic");
		new Label(this, SWT.NONE);

		Button soundCheck = new Button(this, SWT.CHECK);
		soundCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		soundCheck.setText("Sound");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label socketLabel = new Label(this, SWT.NONE);
		socketLabel.setText("Remote server address");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		StyledText addressText = new StyledText(this, SWT.BORDER);
		addressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		addressText.setText("127.0.0.1");

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.BOLD));
		label.setText(":");

		StyledText portText = new StyledText(this, SWT.BORDER);
		portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		portText.setText("5400");

		Button btnSaveTo = new Button(this, SWT.NONE);
		btnSaveTo.setText("Save to...");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		
		
		
		
		
		pack();
	}
	
}

	