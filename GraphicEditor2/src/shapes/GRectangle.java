package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


//
public class GRectangle extends GShape{
	private Rectangle2D.Float rectangle; //��ǥ�� �̰��� ������ ��.
	
	public GRectangle() {
		this.rectangle = new Rectangle2D.Float(0,0,0,0); //rectangle�� �����Ǿ� ��ǥ�� �� 0
		this.shape = this.rectangle; //Association�� ���� �����ϴ� ��
	}
	
	public void setPoint(int x, int y) {
		this.rectangle.setFrame(x, y, 0, 0);
	}
	
	public void dragPoint(int x, int y) {
		double ox = rectangle.getX();
		double oy = rectangle.getY();
		
		double w = x - ox;
		double h = y - oy;
		this.rectangle.setFrame(ox,oy,w,h);
	}
	
	Rectangle getR = new Rectangle();

	
}
