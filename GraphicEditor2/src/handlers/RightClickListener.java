package handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import frames.GShapeMenu;

public class RightClickListener extends MouseAdapter implements MouseListener {
	GShapeMenu shapeM;
	
	public RightClickListener(GShapeMenu sm) {
		this.shapeM = sm;
	}

	@Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            shapeM.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            shapeM.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
