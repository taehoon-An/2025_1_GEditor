package transformer;

import java.awt.Graphics2D;

import frames.GDrawingPanel;
import shapes.GRectangle;
import shapes.GShape;

public class GDrawer implements GTransformer {

private GRectangle rectangle;
private GDrawingPanel panel;

	public GDrawer(GShape shape, GDrawingPanel panel) {
		this.rectangle = (GRectangle)shape;
		this.panel = panel;
	}
	
	public void start(Graphics2D graphics, int x, int y) {
		graphics.setXORMode(this.panel.getBackground());
		this.rectangle.setPoint(x, y);
		this.rectangle.dragPoint(x, y);
	}
	
	public void drag(Graphics2D graphics, int x, int y) {
		graphics.setXORMode(this.panel.getBackground());
		this.rectangle.draw(graphics);
		this.rectangle.dragPoint(x, y);
		this.rectangle.draw(graphics);
	}
	
	public GRectangle finish(Graphics2D graphics, int x, int y) {
		graphics.setPaintMode();
		return rectangle;
	}
	
	
}

