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
		
		this.rectangleButton = new GRecButton("Rectangle"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.rectangleButton);
		
		this.triangleButton = new GTriButton("Triangle"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.triangleButton);
		
		this.ovalButton = new GOvalButton("Oval"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.ovalButton);
		
		this.polygonButton = new GPolButton("Polygon"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.polygonButton);
		
		this.textBoxButton = new GTextButton("Text Box"); //�Ű����� �ڸ��� String�� ���ϴ� ���ڷ� ���ָ� 
		this.add(this.textBoxButton);
		
		
		this.group = new ButtonGroup();//���߼����� ���� ���ϰ� ���� �׷�ȭ
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
