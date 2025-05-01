package shapes;

import java.awt.Polygon;
import java.util.ArrayList;

public class GPolygon extends GShape {
	
    //ArrayList를 모두 지우고 연결한 Polygon을 이용(GRectangle처럼)
    private Polygon polygon;
    
    public GPolygon() {
    	this.polygon = new Polygon(); //GShape가 가진 shape(부모클래스)에 polygon을 저장
    	this.shape = this.polygon;
    }
    
    public void addPoint(int x, int y) {
    	polygon.addPoint(x, y);
    }

    public void updatePoint(int index, int x, int y) {
    	if (index < polygon.xpoints.length) {
            polygon.xpoints[index] = x;
            polygon.ypoints[index] = y;
            System.out.println("updatePoint: index = " + index + ", x = " + x + ", y = " + y);
        }
    }
    

}
