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
import frames.GShapeToolBar.EShapeType;
import shapes.GRectangle;
import shapes.GShape;
import transformer.GTransformer;
import transformer.GDrawer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GTransformer transformer;
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
	private EShapeType eShapeType; //toolbar���� association�� ���� �����ǵ���
	private EDrawingState eDrawingState;
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler); //drawingPanel�� ���콺 �ڵ鷯.
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eShapeType = null;
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
	
	public void setEShapeType(EShapeType eShapeType) { //toolbar���� Type������ �ְ�
		this.eShapeType = eShapeType;
	}
	
	private void startDrawing(int x, int y){
		// set shape
		GShape shape = eShapeType.newShape(); //eshapeType �����ڷ� Shape����
		this.transformer = new GDrawer(shape, GDrawingPanel.this);
		this.transformer.start((Graphics2D)getGraphics(), x, y);
	}
	
	private void keepDrawing(int x, int y) {
		this.transformer.drag((Graphics2D)getGraphics(), x, y);
	}
	
	private void addPoint(int x, int y) {
		
	}
	
	private void finishDrawing(int x, int y) {
		GShape shape = this.transformer.finish((Graphics2D)getGraphics(), x, y);
        shapes.add(shape); // �ϼ��� ������ shapes ���Ϳ� �߰�
        repaint(); // ȭ�� ����
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		//mapping function
		
		private GTransformer transformer;
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClick");
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EDrawingState.eIdle) {
				if(eShapeType == EShapeType.eSelect) {
					//set transformer
				} else {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2P; //�ΰ� ��
					
				}
				
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
		
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("mouseMoved");
			
		}

		
	}
}


