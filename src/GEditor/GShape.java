package GEditor;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTriHandler;
import GShapeMenu.GPopBtDraw;
import GShapeMenu.GPopBtMove;

public class GShape extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	GPopBtDraw drawMenu;
	GPopBtMove moveMenu;
	JMenuItem menuItem3;
	JMenuItem menuItem4;

	public GShape() {		
		this.drawMenu = new GPopBtDraw();
		this.add(drawMenu);
		
		this.moveMenu = new GPopBtMove();
		this.add(moveMenu);
		
		this.menuItem3 = new JMenuItem("Resize");
		this.add(menuItem3);
		
		this.menuItem4 = new JMenuItem("Rotate");
		this.add(menuItem4);
	}

	 public void initialize(GRecHandler handler) {
		 System.out.println("rec button clear");
	        this.drawMenu.initialize(handler);
	        this.moveMenu.initialize(handler);
	    }
	
	 public void initialize(GTriHandler handler) {
		 System.out.println("tri button clear");
		 	this.drawMenu.initialize(handler);
		 	this.moveMenu.initialize(handler);
	 }
	
	 public void initialize(GPolHandler handler) {
		 System.out.println("pol button clear");
		 	this.drawMenu.initialize(handler);
		 	this.moveMenu.initialize(handler);
	 }
	 
	 public void initialize(GOvalHandler handler) {
		 System.out.println("oval button clear");
		 	this.drawMenu.initialize(handler);
		 	this.moveMenu.initialize(handler);
	 }
}
