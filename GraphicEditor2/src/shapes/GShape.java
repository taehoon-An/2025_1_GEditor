package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

//Shape은 기본적으로 그림을 그리도록 해주어야 함. 
public class GShape {
	protected Shape shape;
	
	public GShape() {		
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
}
