package GHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import GButton.GRecButton;
import GEditor.GDrawingPanel;
import GKindOfShapes.GRectangle;

public class GRecHandler implements MouseListener, MouseMotionListener  {
    private GDrawingPanel panel;
    private GTransformer transformer;

    public GRecHandler(GDrawingPanel panel, GTransformer transformer) {
        this.panel = panel;
        this.transformer = transformer;
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
	public void mouseMoved(MouseEvent e) {
		if(transformer.getRecButton() != null && transformer.getRecButton().isSelected() && transformer.tempRectangle != null) {
			System.out.println("drag");
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
    		g2d.setColor(Color.BLUE); //drag할때 특정 색깔지정
    		g2d.setXORMode(panel.getBackground());
    		transformer.drag(g2d, e.getPoint());
		}
        

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(transformer.getRecButton() != null && transformer.getRecButton().isSelected()) {
			System.out.println("getRecButton Click");
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
	        g2d.setXORMode(panel.getBackground());
	        if(transformer.getTempRectangle() == null) {
	        	transformer.start(g2d, e.getPoint());
	        	transformer.setRectangles(panel.getRectangles());
	        } else if(transformer.getTempRectangle() != null) {
	        	transformer.finish();
	        	panel.setRectangles(transformer.getRectangles());
	        	panel.repaint();//없으면 지정한 색깔이 제대로 paint되지 않음. 
	        }
		} 
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}