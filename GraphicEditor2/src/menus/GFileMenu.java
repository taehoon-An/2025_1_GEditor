package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private JMenuItem newItem;
	private JMenuItem newItem2;
	private JMenuItem newItem3;
	private JMenuItem newItem4;
	private JMenuItem newItem5;
	
	
	public GFileMenu() {
		super("File"); 
		
		this.newItem = new JMenuItem("New File"); //구조적으로 추가되거나 변경할 거 없으면 Class만들지 말고 import
		this.add(this.newItem);
		
		this.newItem2 = new JMenuItem("Open");
		this.add(this.newItem2);
		
		this.newItem3 = new JMenuItem("Save");
		this.add(this.newItem3);
		
		this.newItem4 = new JMenuItem("Save As..");
		this.add(this.newItem4);
		
		this.newItem5 = new JMenuItem("Quit");
		this.add(this.newItem5);
	}

	public void initialize() {
		
		
	}
}
