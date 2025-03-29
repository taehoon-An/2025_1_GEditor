package GEditor;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private JMenuItem EnewItem;
	private JMenuItem EnewItem2;
	private JMenuItem EnewItem3;

	
	
	public GEditMenu() {
		super("Edit"); 
		
		this.EnewItem = new JMenuItem("Property"); 
		this.add(this.EnewItem);
		
		this.EnewItem2 = new JMenuItem("Undo");
		this.add(this.EnewItem2);
		
		this.EnewItem3 = new JMenuItem("Redo");
		this.add(this.EnewItem3);
	
	}
		

	public void initialize() {
		
		
	}
}
