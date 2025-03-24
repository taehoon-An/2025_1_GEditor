package GEditor;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(10, 10, 50, 50);
		
	}
	
	public void initialize() {
	
}
	
}
