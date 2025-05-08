package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

//Shape은 기본적으로 그림을 그리도록 해주어야 함. 
public abstract class GShape {
	
	public enum EPoints {
		e2P, //e2P가 eRectangle과 같이 들어가야 함. 
		eNP
	}
	
	private Shape shape; // 인캡슐레이션 
	
	public GShape(Shape shape) {
		this.shape = shape;
	}
	
	protected Shape getShape() {
		return this.shape;
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
	
	public void drawAnchor(Graphics2D graphics2D) {
		graphics2D.setColor(Color.gray);
		Rectangle bounds = this.shape.getBounds();
		int x = bounds.x, 
			y = bounds.y,
			w = bounds.width,
			h = bounds.height;
		
		Point[] anchors = new Point[] {
		  new Point(x, y),             // 상단 좌측 1
		  new Point(x + w/2, y),       // 상단 중앙 2
		  new Point(x + w, y),         // 상단 우측 3
		  new Point(x, y + h/2),       // 좌측 중앙 4
		  new Point(x + w, y + h/2),   // 우측 중앙 5 
		  new Point(x, y + h),         // 하단 좌측 6
		  new Point(x + w/2, y + h),   // 하단 중앙 7
		  new Point(x + w, y + h)      // 하단 우측 8
		};
		for(Point p : anchors) {
			graphics2D.fillOval(p.x - 4, p.y - 4, 8, 8);
		}
		
		graphics2D.draw(shape.getBounds());
		graphics2D.setColor(Color.black);
	}
	
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return this.shape.contains(x, y);
	}
	
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y) ;
	public abstract void dragPoint(int x, int y) ;

	public abstract void setMovePoint(int x, int y);
	public abstract void movePoint(int x, int y);

	

}
