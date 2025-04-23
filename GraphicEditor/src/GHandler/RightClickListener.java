package GEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RightClickListener extends MouseAdapter implements MouseListener {
	GShape shapeM;
	
	public RightClickListener(GShape sm) {
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
