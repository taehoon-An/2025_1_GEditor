package GHandler;

import GButton.GPolLineButton;
import GEditor.GDrawingPanel;
import GKindOfShapes.GPolLine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GPolLineHandler implements MouseListener, MouseMotionListener {
    private GDrawingPanel panel;
    private GTransformer transformer;
    private GPolLineButton polLineButton;
    private GPolLine currentPolLine;
    private Point lastPoint;
    private boolean isMoving = false;

    public GPolLineHandler(GDrawingPanel panel, GTransformer transformer) {
        this.panel = panel;
        this.transformer = transformer;
    }

    public void setPolLineButton(GPolLineButton polLineButton) {
        this.polLineButton = polLineButton;
        this.transformer.setPolLineButton(polLineButton);
    }

    public void mouseClicked(MouseEvent e) {
        if (transformer.getPolLineButton() != null && transformer.getPolLineButton().isSelected()) {
            System.out.println("getPlButton Click");
            Graphics2D g2d = (Graphics2D) panel.getGraphics();
            g2d.setXORMode(panel.getBackground());
            if(e.getClickCount() == 1) {
            	transformer.start(g2d, e.getPoint());
            	transformer.setPolLines(panel.getPolLines());
            	panel.repaint();
            } else if(e.getClickCount() == 2) {
            	transformer.finish();
            	panel.setPolLines(transformer.getPolLines());
            	panel.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(transformer.getPolLineButton() != null && transformer.getPolLineButton().isSelected() && transformer.tempLine != null) {
   //     	System.out.println("drag");
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
    		g2d.setColor(Color.BLUE); //drag할때 특정 색깔지정
    		g2d.setXORMode(panel.getBackground());
    		transformer.drag(g2d, e.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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