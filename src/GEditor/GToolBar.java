package GEditor;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

import GButton.GOvalButton;
import GButton.GPolButton;
import GButton.GRecButton;
import GButton.GTextButton;
import GButton.GTriButton;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	GRecButton rectangleButton;
	GTriButton triangleButton;
	GOvalButton ovalButton;
	GPolButton polygonButton;
	GTextButton textBoxButton;

	private ButtonGroup group;
	
	
	public GToolBar() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.rectangleButton = new GRecButton("Rectangle"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.rectangleButton);
		
		this.triangleButton = new GTriButton("Triangle"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.triangleButton);
		
		this.ovalButton = new GOvalButton("Oval"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.ovalButton);
		
		this.polygonButton = new GPolButton("Polygon"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.polygonButton);
		
		this.textBoxButton = new GTextButton("Text Box"); //매개변수 자리에 String을 원하는 문자로 써주면 
		this.add(this.textBoxButton);
		
		
		this.group = new ButtonGroup();//다중선택이 되지 못하게 막는 그룹화
		group.add(this.rectangleButton);
		group.add(this.triangleButton);
		group.add(this.ovalButton);
		group.add(this.polygonButton);
		group.add(this.textBoxButton);
	}

	public void initialize() {
		this.rectangleButton.initialize();
		this.triangleButton.initialize();
		this.ovalButton.initialize();
		this.polygonButton.initialize();
		this.textBoxButton.initialize();
	}
	
	public GRecButton getRecButton() {
		return this.rectangleButton;
	}
	
	public GTriButton getTriButton() {
		return this.triangleButton;
	}
	
	public GOvalButton getOvalButton() {
		return this.ovalButton;
	}
	
	public GPolButton getPolButton() {
		return this.polygonButton;
	}
	
	public GTextButton getTextButton() {
        return this.textBoxButton;
    }
	
	
}
