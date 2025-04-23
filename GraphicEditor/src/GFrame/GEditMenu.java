package GEditor;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private JMenuItem EnewItem;
	private JMenuItem EnewItem2;

	
	
	public GEditMenu() {
		super("Edit"); 
		
		this.EnewItem = new JMenuItem("Property"); 
		this.add(this.EnewItem);
		
		this.EnewItem2 = new JMenuItem("Undo/Redo");
		this.add(this.EnewItem2);
	
	
	}
		

	public void initialize() {
		
		
	}
}
