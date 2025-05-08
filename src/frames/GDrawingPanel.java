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
import shapes.GShape.EPoints;
import transformer.GTransformer;
import transformer.GDrawer;
import transformer.GMover;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//Toolbar에서 이 Type중 하나를 선택하게 될 것임. 
	
	public enum EDrawingState {
		eIdle,
		e2P,
		eNP
	}
	
	private Vector<GShape> shapes;
	private GTransformer transformer;
	private GShape currentShape;
	private GShape selectedShape;
	private EShapeTool eShapeTool; //toolbar에서 association에 의해 설정되도록
	private EDrawingState eDrawingState;
	
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler); //drawingPanel용 마우스 핸들러.
		this.addMouseMotionListener(mouseHandler);
		
		this.currentShape = null;
		this.selectedShape = null;
		this.shapes = new Vector<GShape>();
		this.eShapeTool = null;
		this.eDrawingState = EDrawingState.eIdle;
	}
	
	@Override
	public void paintComponent(Graphics g) { //paintComponent는 화면이 갱신될 때마다 자동으로 호출되는 메서드
		super.paintComponent(g);
		for(GShape shape : this.shapes) {
			shape.draw((Graphics2D)g);
		}
		
		if(this.selectedShape != null) {
			selectedShape.drawAnchor((Graphics2D) g);
		} 
		
	}
	
	public void initialize() {

	}
	
	public void setEShapeTool (EShapeTool eShapeTool) { //toolbar에서 Type지정후 넣게
		this.eShapeTool = eShapeTool;
	}
	
	private GShape onShape(int x, int y) {
		for(GShape shape : this.shapes) {
			if (shape.contains(x,y)) {
				return shape;
			}
		}
		return null;
	}
	
	private void startTransform(int x, int y){
		// set shape
		
		this.currentShape = eShapeTool.newShape(); //eshapeType 생성자로 Shape지정
		this.shapes.add(currentShape);
		if(this.eShapeTool == EShapeTool.eSelect) {
			this.selectedShape = onShape(x, y);
			if(this.selectedShape == null) {
				this.transformer = new GDrawer(currentShape);
			} else {
				this.transformer = new GMover(selectedShape);
			}
		} else {
			this.transformer = new GDrawer(currentShape);
		}
		this.transformer.start((Graphics2D)getGraphics(), x, y);
		this.repaint();
		
	} 
	
	private void keepTransform(int x, int y) {
		this.transformer.drag((Graphics2D)getGraphics(), x, y);
		this.repaint();
	}
	
	private void addPoint(int x, int y) {
		this.transformer.addPoint((Graphics2D)getGraphics(), x, y);
	}
	
	private void finishTransform(int x, int y) {
		this.transformer.finish((Graphics2D)getGraphics(), x, y);
		if(this.eShapeTool == EShapeTool.eSelect) {
			this.shapes.remove(this.shapes.size() - 1);
		}
		this.transformer = null;
		this.selectedShape = null;
        this.repaint(); // 화면 갱신
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		//mapping function
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1) {
				this.mouse1Clicked(e);
				
			} else if(e.getClickCount() == 2) {
				this.mouse2Clicked(e);
			}
			
		}
		
		public void mouse1Clicked(MouseEvent e) {
			if(eDrawingState == EDrawingState.eIdle) { //기본 상태일때
				if(eShapeTool.getEPoints() == EPoints.eNP) {
					startTransform(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNP; //State가 eNP로
					
				} if(eShapeTool.getEPoints() == EPoints.e2P) {
					startTransform(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2P; //State가 e2P로
					
				}
				
			} else if(eDrawingState == EDrawingState.eNP) { //eNP상태일떄
				if(eShapeTool.getEPoints() == EPoints.eNP) {
					addPoint(e.getX(), e.getY());
				}
				
			}  else if (eDrawingState == EDrawingState.e2P) { //e2P상태일때
				finishTransform(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		
		public void mouse2Clicked(MouseEvent e) {
			if(eShapeTool.getEPoints() == EPoints.eNP) {
				finishTransform(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EDrawingState.eNP) {
				keepTransform(e.getX(), e.getY());
			} else if(eDrawingState == EDrawingState.e2P) {
				keepTransform(e.getX(), e.getY());
			}
			 
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			
			
		}					
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {	
			
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


