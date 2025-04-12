package GKindOfShapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GPolLine extends Polygon {
	private ArrayList<Integer> xpointsList = new ArrayList<>();
    private ArrayList<Integer> ypointsList = new ArrayList<>();
    
    public GPolLine() {
    }
    
    public void addPoint(int x, int y) {
        xpointsList.add(x);
        ypointsList.add(y);
    }

    public void updatePoint(int index, int x, int y) {
    	if (index < xpointsList.size()) {
            xpointsList.set(index, x);
            ypointsList.set(index, y);
            System.out.println("updatePoint: index = " + index + ", x = " + x + ", y = " + y);
        }
    }
    
    public int getPointCount() {
    	return xpointsList.size();
    }
    
    public void polLinedraw(Graphics2D g) {
    	for(int i = 0; i < 
    			xpointsList.size() - 1; i++ ) {
    		System.out.println("working");
    		g.drawLine(xpointsList.get(i), ypointsList.get(i), xpointsList.get(i + 1), ypointsList.get(i + 1));
    	}
    }
    
    public void tempPolLineDraw(Graphics2D g) {
    	//지금 움직이는 선만 draw
    	int x1 = xpointsList.get(xpointsList.size()-2);
    	int y1 = ypointsList.get(ypointsList.size()-2);
    	int x2 = xpointsList.get(xpointsList.size()-1);
    	int y2 = ypointsList.get(ypointsList.size()-1);
    	g.drawLine(x1, y1, x2, y2);
    }

 
}