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
		//�ϴ� GRectangle�ۿ� ������ GRectangle��
		eSelect("select", EDrawingType.e2P, GRectangle.class),
		eRectangle("Rectangle", EDrawingType.e2P, GRectangle.class),
		eEllipse("Ellipse", EDrawingType.e2P, GRectangle.class),
		eLine("Line", EDrawingType.e2P, GRectangle.class),
		ePolygon("Polygon",EDrawingType.eNP, GPolygon.class);
		
		private String name;
		private EDrawingType eDrawingType;
		private Class<?> classShape; //Ÿ�Թ�����
		
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
		
		public GShape newShape() { //���� �̰� �θ��⸸ �ϸ� ��. �� New�ν��Ͻ� ���ִ� ��. 
			try {//�̰� �θ��� ���� panel���� ���� ���� �����ϰ�, ���� �߸��Ȱ���
				//Rectangle������ ����� Transformer������ �ƴ� Panel���� ���� �� transformer�� �Űܾ� ��
				GShape shape = (GShape) classShape.getConstructor().newInstance();
				return shape;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} // shape ��ü ����
			return null;
		}
	}
	
	//asociations
	private GDrawingPanel drawingPanel;
	
	
	public GShapeToolBar() {
		ButtonGroup group = new ButtonGroup();//���߼����� ���� ���ϰ� ���� �׷�ȭ
		for(EShapeTool eShapeType : EShapeTool.values()) {//enum�� Arrayó�� �۵��ϱ⿡ .values���
			JRadioButton radioButton = new JRadioButton(eShapeType.getName()); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ�
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
