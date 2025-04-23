package frames;

import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;
import menus.GGraphicMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	//Compoenent(ÀÚ½Ä)
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GGraphicMenu gMenu;
	
	//Association(friends)
	private GDrawingPanel drawingPanel;
	
	
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

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		
	}
}
