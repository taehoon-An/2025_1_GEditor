package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JPanel;

import frames.GDrawingPanel.EDrawingState;
import frames.GShapeToolBar.EShapeTool;
import shapes.GRectangle;
import shapes.GShape;
import transformer.GTransformer;
import transformer.GDrawer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//Toolbar���� �� Type�� �ϳ��� �����ϰ� �� ����. 
	
	public enum EDrawingType {
		e2P, //e2P�� eRectangle�� ���� ���� ��. 
		eNP
	}
	
	public enum EDrawingState {
		eIdle,
		e2P,
		eNP
	}
	
	private Vector<GShape> shapes;
	private GTransformer transformer;
	private GShape currentShape;
	private EShapeTool eShapeTool; //toolbar���� association�� ���� �����ǵ���
	private EDrawingState eDrawingState;
	
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler); //drawingPanel�� ���콺 �ڵ鷯.
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eShapeTool = null;
		this.eDrawingState = EDrawingState.eIdle;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(GShape shape : this.shapes) {
			shape.draw((Graphics2D)g);
		}
	}
	
	public void initialize() {

	}
	
	public void setEShapeTool (EShapeTool eShapeTool) { //toolbar���� Type������ �ְ�
		this.eShapeTool = eShapeTool;
	}
	
	private void startDrawing(int x, int y){
		// set shape
		if(eDrawingState == EDrawingState.eIdle) {
			this.currentShape = eShapeTool.newShape(); //eshapeType �����ڷ� Shape����
			this.shapes.add(currentShape);
			this.transformer = new GDrawer(currentShape, eShapeTool);
		}
		this.transformer.start((Graphics2D)getGraphics(), x, y);
	} 
	
	private void keepDrawing(int x, int y) {
		this.transformer.drag((Graphics2D)getGraphics(), x, y);
		this.repaint();
	}
	
	private void addPoint(int x, int y) {
		
	}
	
	private void finishDrawing(int x, int y) {
		this.transformer.finish((Graphics2D)getGraphics(), x, y);
		this.transformer = null;
        this.repaint(); // ȭ�� ����
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		//mapping function
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClick");
			if(eDrawingState == EDrawingState.eIdle) {
				if(eShapeTool.getEDrawingType() == EDrawingType.eNP && eShapeTool.getName() == EShapeTool.ePolygon.getName()) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNP;
				}
			} else if(eDrawingState == EDrawingState.eNP && e.getClickCount() == 1) {
				if(eShapeTool.getEDrawingType() == EDrawingType.eNP && eShapeTool.getName() == EShapeTool.ePolygon.getName()) {
					startDrawing(e.getX(), e.getY());
				}
			} else if(eDrawingState == EDrawingState.eNP && e.getClickCount() == 2) { //����
				if(eShapeTool.getEDrawingType() == EDrawingType.eNP && eShapeTool.getName() == EShapeTool.ePolygon.getName()) {
					finishDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			}
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EDrawingState.eNP) {
				keepDrawing(e.getX(), e.getY());
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EDrawingState.eIdle) {
				if(eShapeTool.getEDrawingType() == EDrawingType.e2P && eShapeTool.getName() == EShapeTool.eRectangle.getName()) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2P; //State�� e2P��
				} 
				
			//	if(eShapeTool.getEDrawingType() == EDrawingType.)
				
			}
			
			
		}					
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EDrawingState.e2P) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {	
			if (eDrawingState == EDrawingState.e2P) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("mouseEntered");
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("mouseExited");
			
		}

		
	}
}


