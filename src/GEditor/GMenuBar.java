package GEditor;

import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GGraphicMenu gMenu;
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu();
		this.add(this.fileMenu);
		
		this.editMenu = new GEditMenu();
		this.add(editMenu);
		
		this.gMenu = new GGraphicMenu();
		this.add(gMenu);
	}

	public void initialize() {
		this.fileMenu.initialize();
		
	}
}
