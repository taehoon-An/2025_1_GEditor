package GEditor;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GGraphicMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem GnewItem;
	private JMenuItem GnewItem2;
	private JMenuItem GnewItem3;
	private JMenuItem GnewItem4;
	
	
	public GGraphicMenu() {
		super("Graphic"); 
		
		this.GnewItem = new JMenuItem("Line thickness");
		this.add(this.GnewItem);
		
		this.GnewItem2 = new JMenuItem("Line Style");
		this.add(this.GnewItem2);
		
		this.GnewItem3 = new JMenuItem("Font style");
		this.add(this.GnewItem3);
		
		this.GnewItem4 = new JMenuItem("Font size");
		this.add(this.GnewItem4);
		

	}

	public void initialize() {
		
		
	}
}
