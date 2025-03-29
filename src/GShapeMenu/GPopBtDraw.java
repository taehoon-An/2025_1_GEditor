package GShapeMenu;

import javax.swing.JMenuItem;

import GHandler.GRecHandler;
import GHandler.GTriHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPopBtDraw extends JMenuItem { //overloading을 활용
    private static final long serialVersionUID = 1L;
    private GRecHandler recHandler;
    private GTriHandler triHandler;

    public GPopBtDraw() {
        super("Draw");
        
    }
    
    public void initialize(GRecHandler handler) {
        this.recHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(handler != null) {
                handler.setMovingMode(false);
                System.out.println("Draw 선택");
            	} else {
                	System.out.println("Draw 선택안됨");
                }
            }
            
        });

    }
    
    public void initialize(GTriHandler handler) { 
        this.triHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(handler != null) {
                handler.setMovingMode(false);
                System.out.println("Draw 선택");
            	} else {
                	System.out.println("Draw 선택안됨");
                }
            }
            
        });

    }
}