package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.custom.StyledText;

import singletonexplicitpack.Properties;

public class PropertiesWidget extends Composite {

	/***
	 * 
	 * this class is a part of the GUI view menu it holds the user properties
	 * choice and the path to save it an outside selection listener is injected
	 * to the button in order to get the details
	 */

	public Properties prop;
	public String Path;
	public Button btnSaveTo;

	StyledText portText;
	Label lblSettings;

	Label uiLabel;
	Combo uiCombo;

	Label genLabel;
	Combo genCombo;

	Label solveLabel;
	Combo solveCombo;

	Combo heurCombo;

	Button soundCheck;

	Label socketLabel;

	StyledText addressText;

	Label dotslabel;

	public PropertiesWidget(Composite parent, int style) {
		super(parent, style);

		 lblSettings = new Label(this, SWT.NONE);
		lblSettings.setText("Settings");

		FontData[] fontData = lblSettings.getFont().getFontData();
		for (int i = 0; i < fontData.length; ++i) {
			fontData[i].setHeight(14);
			fontData[i].setStyle(SWT.BOLD);
		}
		final Font newFont = new Font(parent.getDisplay(), fontData);
		lblSettings.setFont(newFont);

		// i created the font so i must dispose it
		lblSettings.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				newFont.dispose();
			}
		});

		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		 uiLabel = new Label(this, SWT.NONE);
		uiLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		uiLabel.setText("User Interface");
		new Label(this, SWT.NONE);

		 uiCombo = new Combo(this, SWT.NONE);
		uiCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		uiCombo.setItems(new String[] { "GUI", "CLI" });
		new Label(this, SWT.NONE);

		genLabel = new Label(this, SWT.NONE);
		genLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		genLabel.setText("Maze generating algorithm");
		new Label(this, SWT.NONE);

		genCombo = new Combo(this, SWT.NONE);
		genCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		genCombo.setItems(new String[] { "simple", "dfs" });
		new Label(this, SWT.NONE);

		solveLabel = new Label(this, SWT.NONE);
		solveLabel.setText("Maze solving algorithm");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		solveCombo = new Combo(this, SWT.NONE);
		solveCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		solveCombo.setItems(new String[] { "BFS", "Astar" });
		new Label(this, SWT.NONE);

		heurCombo = new Combo(this, SWT.NONE);
		heurCombo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		heurCombo.setItems(new String[] { "Manhatten", "AirDistance" });
		heurCombo.setText("Astar Heuristic");
		new Label(this, SWT.NONE);

		soundCheck = new Button(this, SWT.CHECK);
		
		soundCheck.setText("Sound");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		

		socketLabel = new Label(this, SWT.NONE);
		socketLabel.setText("Remote server address");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		addressText = new StyledText(this, SWT.BORDER);
		addressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		addressText.setText("127.0.0.1");

		dotslabel = new Label(this, SWT.NONE);
		dotslabel.setText(":");
		FontData[] dotsfontData = dotslabel.getFont().getFontData();
		for (int i = 0; i < dotsfontData.length; ++i) {
			dotsfontData[i].setHeight(10);
			dotsfontData[i].setStyle(SWT.BOLD);
		}
		final Font dotsFont = new Font(parent.getDisplay(), dotsfontData);
		dotslabel.setFont(dotsFont);

		// i created the font so i must dispose it
		lblSettings.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				newFont.dispose();
			}
		});

		 portText = new StyledText(this, SWT.BORDER);
		portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		portText.setText("5400");

		 btnSaveTo = new Button(this, SWT.NONE);
		btnSaveTo.setText("Save to...");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		btnSaveTo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(  
						addressText.getText().matches("\\d+\\.\\d+\\.\\d+\\.\\d+")    &&
						portText.getText().matches("\\d+")  && ( uiCombo.getText().matches("[Cc][Ll][Ii]") || uiCombo.getText().matches("[Gg][Uu][Ii]") ) && 
						( genCombo.getText().matches("[Ss][Ii][Mm][Pp][Ll][Ee]") || genCombo.getText().matches("[Dd][Ff][Ss]") ) &&
								   (           solveCombo.getText().matches("[Bb][Ff][Ss]")    ||        solveCombo.getText().matches("[Aa][Ss][Tt][Aa][Rr]")    )   &&
										( heurCombo.getText().matches("[Mm][Aa][Mn][Hh][Aa][Tt][Tt][Ee][Nn]")|| heurCombo.getText().matches("[Aa][Ii][Rr][Dd][Ii][Ss][Tt][Aa][Nn][Cc][Ee]") )  
				 )						
				prop= new Properties(addressText.getText(), Integer.parseInt(portText.getText()), uiCombo.getText(), genCombo.getText(), solveCombo.getText(), heurCombo.getText(), soundCheck.getSelection());
				else{
					MessageBox messageBox = new MessageBox(getShell(),SWT.ICON_ERROR);
			    messageBox.setMessage("Message");
			    messageBox.open();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				
			}
		});
		
		
		
		
		
		
		pack();
	
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

}
