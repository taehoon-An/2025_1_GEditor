package GShapeMenu;

import javax.swing.JMenuItem;

import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTextHandler;
import GHandler.GTransformer;
import GHandler.GTriHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPopBtMove extends JMenuItem {
    private static final long serialVersionUID = 1L;
    private GRecHandler recHandler;
    private GTriHandler triHandler;
    private GPolHandler polHandler;
    private GOvalHandler ovalHandler;
    private GTextHandler textHandler;
    
    private GTransformer transformer;

    public GPopBtMove() {
        super("Move");
        this.transformer = new GTransformer();
        

    }
    
    public void setTransformer(GTransformer transformer) {
    	this.transformer = transformer;
    }
    
    
    public void initialize(GRecHandler handler) {
    	this.recHandler = handler;
    	this.addActionListener(new ActionListener() { //만약 Move메뉴선택됐으면
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                transformer.setMovingMode(true);//MovingMode 활성화
                System.out.println("Move모드 선택");
            	}
            }
        }); 
    }
    
    public void initialize(GTriHandler handler) {
    	this.triHandler = handler;
    	this.addActionListener(new ActionListener() { //만약 Move메뉴선택됐으면
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode 활성화
                System.out.println("Move모드 선택");
            	}
            }
        }); 
    }
    
    public void initialize(GPolHandler handler) {
    	this.polHandler = handler;
    	this.addActionListener(new ActionListener() { //만약 Move메뉴선택됐으면
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode 활성화
                System.out.println("Move모드 선택");
            	}
            }
        }); 
    }
    
    public void initialize(GOvalHandler handler) {
    	this.ovalHandler = handler;
    	this.addActionListener(new ActionListener() { //만약 Move메뉴선택됐으면
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode 활성화
                System.out.println("Move모드 선택");
            	}
            }
        }); 
    }
    
    public void initialize(GTextHandler handler) {
        this.textHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(handler != null) {
                    handler.setMovingMode(true);
                    System.out.println("Move모드 선택");
                }
            }
        });
    }
}