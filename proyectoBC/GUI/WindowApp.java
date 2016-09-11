package proyectoBC.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import proyectoBC.engine.GameEngine;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
import proyectoBC.keyboard.ThreadKeyboard;

import org.eclipse.swt.widgets.Label;

public class WindowApp {

	protected Shell shell;
	protected GameEngine ge;
	protected TanqueJugador tj;
	protected ThreadKeyboard tkeyboard;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowApp window = new WindowApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(0, 0, 0));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		/* Game engine and TanqueJugador */
		ge = new GameEngine();
		tj = new TanqueJugador(0,0,ge);
		tkeyboard= new ThreadKeyboard(ge);
		
		Label label1 = new Label(shell, SWT.NONE);
		label1.setImage(SWTResourceManager.getImage(WindowApp.class, tj.getImages()[0]));
		label1.setBounds(36, 139, 24, 24);
		
		Label label2 = new Label(shell, SWT.NONE);
		label2.setImage(SWTResourceManager.getImage(WindowApp.class, tj.getImages()[1]));
		label2.setBounds(120, 139, 24, 24);
		
		Label label3 = new Label(shell, SWT.NONE);
		label3.setImage(SWTResourceManager.getImage(WindowApp.class, tj.getImages()[2]));
		label3.setBounds(223, 139, 24, 24);
		
		Label label4 = new Label(shell, SWT.NONE);
		label4.setImage(SWTResourceManager.getImage(WindowApp.class, tj.getImages()[3]));
		label4.setBounds(332, 139, 24, 24);
		
		

	}
}
