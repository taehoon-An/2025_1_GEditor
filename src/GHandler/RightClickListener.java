package GHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GEditor.GShape;

public class RightClickListener extends MouseAdapter implements MouseListener {
	GShape shapeM;
	
	public RightClickListener(GShape sm) {
		this.shapeM = sm;
	}

	@Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
        	System.out.println("Pop프레스");
            shapeM.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
        	System.out.println("Pop릴리즈");
            shapeM.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
