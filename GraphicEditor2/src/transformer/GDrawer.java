package transformer;

import java.awt.Graphics2D;

import frames.GDrawingPanel;
import frames.GShapeToolBar.EShapeTool;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;

public class GDrawer extends GTransformer {

private GRectangle rectangle;
private GPolygon polygon;
private EShapeTool eShapeTool;

private int selectedPolygonIndex = 0;

	public GDrawer(GShape shape, EShapeTool eShapeTool) {
		super(shape);
		this.eShapeTool = eShapeTool;
		
	}
	
	public void start(Graphics2D graphics, int x, int y) {
		if(eShapeTool.getName() == EShapeTool.eRectangle.getName()) {
			this.rectangle = (GRectangle) shape;
			this.rectangle.setPoint(x, y);
			this.rectangle.dragPoint(x, y);
		} else if(eShapeTool.getName() == EShapeTool.ePolygon.getName()) {
			this.polygon = (GPolygon) shape;
			if(selectedPolygonIndex == 0) {
				System.out.println("start");
				selectedPolygonIndex++;
				System.out.println(selectedPolygonIndex);
				this.polygon.addPoint(x,y);
				this.polygon.addPoint(x,y);
			} else if(selectedPolygonIndex > 0) {
				System.out.println("add");
				this.polygon.addPoint(x, y);
				selectedPolygonIndex++;
			}
		}
	}
	
	public void drag(Graphics2D graphics, int x, int y) {
		if(eShapeTool.getName() == EShapeTool.eRectangle.getName()) {
			this.rectangle.dragPoint(x, y);
		} else if(eShapeTool.getName() == EShapeTool.ePolygon.getName()) {
			this.polygon.updatePoint(selectedPolygonIndex, x, y);
		}

	}
	
	public void finish(Graphics2D graphics, int x, int y) {
		rectangle = null;
		polygon = null;
		selectedPolygonIndex = 0;
	}
	
	
}

