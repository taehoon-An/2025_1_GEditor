package shapes;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class GPolygon extends GShape {
	private Polygon polygon;

	public GPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}
	

	@Override
	public void setPoint(int x, int y) {
		this.polygon.addPoint(x, y);
		System.out.println(this.polygon.npoints);
		this.polygon.addPoint(x, y);
		System.out.println(this.polygon.npoints);
		
	}

	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
		System.out.println(this.polygon.npoints);
		
	}

	@Override
	public void dragPoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints -1] = x;
		this.polygon.ypoints[this.polygon.npoints -1] = y;
		
		
		
	}

	private int px, py;
	@Override
	public void setMovePoint(int x, int y) {
		this.px = x;
		this.py = y;
		
	}

	@Override
	public void movePoint(int x, int y) {
		int dx = x - px;
		int dy = y - py;
		
//		for(int i = 0; i < this.polygon.npoints; i++) {
//			this.polygon.xpoints[i] += dx;
//			this.polygon.ypoints[i] += dy;
//		}
		this.polygon.translate(dx, dy);
		
		this.px = x;
		this.py = y;
		
	}
    

}
