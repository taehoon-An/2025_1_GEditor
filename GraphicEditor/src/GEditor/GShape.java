package GEditor;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GShape extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	JMenuItem menuItem;
	JMenuItem menuItem2;
	JMenuItem menuItem3;
	JMenuItem menuItem4;

	public GShape() {		
		this.menuItem = new JMenuItem("Draw");
		this.add(menuItem);
		
		this.menuItem2 = new JMenuItem("Move");
		this.add(menuItem2);
		
		this.menuItem3 = new JMenuItem("Resize");
		this.add(menuItem3);
		
		this.menuItem4 = new JMenuItem("Rotate");
		this.add(menuItem4);
	}
	
	
	
}
