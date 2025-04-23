package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


//
public class GRectangle extends GShape{
	private Rectangle2D.Float rectangle; //좌표를 이곳에 저장할 것.
	
	public GRectangle() {
		this.rectangle = new Rectangle2D.Float(0,0,0,0); //rectangle이 생성되어 좌표가 다 0
		this.shape = this.rectangle; //Association을 통해 연결하는 것
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
