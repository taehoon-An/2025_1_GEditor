package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

//Shape�� �⺻������ �׸��� �׸����� ���־�� ��. 
public abstract class GShape {
	
	public enum EPoints {
		e2P, //e2P�� eRectangle�� ���� ���� ��. 
		eNP
	}
	
	private Shape shape; // ��ĸ�����̼� 
	
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
		  new Point(x, y),             // ��� ���� 1
		  new Point(x + w/2, y),       // ��� �߾� 2
		  new Point(x + w, y),         // ��� ���� 3
		  new Point(x, y + h/2),       // ���� �߾� 4
		  new Point(x + w, y + h/2),   // ���� �߾� 5 
		  new Point(x, y + h),         // �ϴ� ���� 6
		  new Point(x + w/2, y + h),   // �ϴ� �߾� 7
		  new Point(x + w, y + h)      // �ϴ� ���� 8
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
