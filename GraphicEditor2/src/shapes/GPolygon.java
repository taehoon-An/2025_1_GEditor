package shapes;

import java.awt.Polygon;
import java.util.ArrayList;

public class GPolygon extends GShape {
	
    //ArrayList�� ��� ����� ������ Polygon�� �̿�(GRectangleó��)
    private Polygon polygon;
    
    public GPolygon() {
    	this.polygon = new Polygon(); //GShape�� ���� shape(�θ�Ŭ����)�� polygon�� ����
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
