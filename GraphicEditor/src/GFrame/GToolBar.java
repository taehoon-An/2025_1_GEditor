package GEditor;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	JRadioButton rectangleButton;
	JRadioButton triangleButton;
	JRadioButton ovalButton;
	JRadioButton polygonButton;
	JRadioButton textBoxButton;

	private ButtonGroup group;
	
	
	public GToolBar() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.rectangleButton = new JRadioButton("Rectangle"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.rectangleButton);
		
		this.triangleButton = new JRadioButton("Triangle"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.triangleButton);
		
		this.ovalButton = new JRadioButton("Oval"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.ovalButton);
		
		this.polygonButton = new JRadioButton("Polygon"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.polygonButton);
		
		this.textBoxButton = new JRadioButton("Text Box"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.textBoxButton);
		
		
		this.group = new ButtonGroup();//���߼����� ���� ���ϰ� ���� �׷�ȭ
		group.add(this.rectangleButton);
		group.add(this.triangleButton);
		group.add(this.ovalButton);
		group.add(this.polygonButton);
		group.add(this.textBoxButton);
	}

	public void initialize() {
		
		
	}
	
}
