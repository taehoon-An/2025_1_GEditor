package GKindOfShapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//주어지는 정보로 rectangle관리 (draw
public class GRectangle extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public GRectangle(int x,int y,int w,int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public void setPoint(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}


	public void recDraw(Graphics2D g) { 
        g.drawRect(x, y, width, height);
        
	}
}