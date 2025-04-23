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
		
		this.rectangleButton = new JRadioButton("Rectangle"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.rectangleButton);
		
		this.triangleButton = new JRadioButton("Triangle"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.triangleButton);
		
		this.ovalButton = new JRadioButton("Oval"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.ovalButton);
		
		this.polygonButton = new JRadioButton("Polygon"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.polygonButton);
		
		this.textBoxButton = new JRadioButton("Text Box"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.textBoxButton);
		
		
		this.group = new ButtonGroup();//다중선택이 되지 못하게 막는 그룹화
		group.add(this.rectangleButton);
		group.add(this.triangleButton);
		group.add(this.ovalButton);
		group.add(this.polygonButton);
		group.add(this.textBoxButton);
	}

	public void initialize() {
		
		
	}
	
}
