import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.shigeodayo.ardrone.ARDroneInterface;

import scratch.ScratchInterface;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;


public class ScratchArdroneWindow implements Runnable {

	protected Shell shell;
	private Thread thread;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScratchArdroneWindow window = new ScratchArdroneWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ScratchArdroneWindow(){
		this.thread = new Thread(this);
	}
	
	public void show(){
		this.thread.start();
	}

	/**
	 * Open the window.
	 */
	private void open() {
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
		shell.setSize(612, 412);
		shell.setText("SWT Application");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.NONE);
		mntmFile.setText("File");
	}

	@Override
	public void run() {
		this.open();
	}
	
	public void async(Runnable runnable){
		this.shell.getDisplay().asyncExec(runnable);
	}
}
