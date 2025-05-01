package frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import frames.GDrawingPanel.EDrawingType;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	
	public enum EShapeTool {
		//일단 GRectangle밖에 없으니 GRectangle로
		eSelect("select", EDrawingType.e2P, GRectangle.class),
		eRectangle("Rectangle", EDrawingType.e2P, GRectangle.class),
		eEllipse("Ellipse", EDrawingType.e2P, GRectangle.class),
		eLine("Line", EDrawingType.e2P, GRectangle.class),
		ePolygon("Polygon",EDrawingType.eNP, GPolygon.class);
		
		private String name;
		private EDrawingType eDrawingType;
		private Class<?> classShape; //타입미지정
		
		private EShapeTool(String name, EDrawingType eDrawingType, Class<?> classShape) {
			this.name = name;
			this.eDrawingType = eDrawingType;
			this.classShape = classShape;
		}
		
		
		public String getName() {
			return this.name;
		}
		
		public EDrawingType getEDrawingType() {
			return this.eDrawingType;
		}
		
		public GShape newShape() { //이제 이걸 부르기만 하면 됨. 즉 New인스턴스 해주는 것. 
			try {//이걸 부르면 이제 panel에선 새로 만들어서 전달하고, 또한 잘못된것이
				//Rectangle에서도 사실은 Transformer에서가 아닌 Panel에서 생성 후 transformer로 옮겨야 함
				GShape shape = (GShape) classShape.getConstructor().newInstance();
				return shape;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} // shape 객체 생성
			return null;
		}
	}
	
	//asociations
	private GDrawingPanel drawingPanel;
	
	
	public GShapeToolBar() {
		ButtonGroup group = new ButtonGroup();//다중선택이 되지 못하게 막는 그룹화
		for(EShapeTool eShapeType : EShapeTool.values()) {//enum은 Array처럼 작동하기에 .values사용
			JRadioButton radioButton = new JRadioButton(eShapeType.getName()); //매개변수 자리에 String을 원하는 문자로 써주면
			ActionHandler actionHandler = new ActionHandler();
			radioButton.addActionListener(actionHandler);
			radioButton.setActionCommand(eShapeType.toString());
			
			this.add(radioButton);	
			group.add(radioButton);
		}
		

		
		group.add(new JRadioButton("yes"));
	}

	public void initialize() {
		
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sShapeType = e.getActionCommand();
			EShapeTool eShapeType = EShapeTool.valueOf(sShapeType);
			drawingPanel.setEShapeTool(eShapeType);
			System.out.println(eShapeType.getName());
		}
		
	}
	
}
