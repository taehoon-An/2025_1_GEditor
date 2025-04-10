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

public class GPopBtDraw extends JMenuItem { //overloading�� Ȱ��
    private static final long serialVersionUID = 1L;
    private GRecHandler recHandler;
    private GTriHandler triHandler;
    private GPolHandler polHandler;
    private GOvalHandler ovalHandler;
    private GTextHandler textHandler;
    
    private GTransformer transformer;

    public GPopBtDraw() {
        super("Draw");

        
    }
    
    public void setTransformer(GTransformer transformer) {
    	System.out.println(transformer);
    	this.transformer = transformer;
    }
    
    //overloading�ؼ� �� �Ȱ��� �̸��� �޼��忡�� �Ű������� �ٸ��� �޼��� ����
    public void initialize(GRecHandler handler) {
        this.recHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(handler != null) {
                transformer.setMovingMode(false);
                System.out.println("Draw ����");
            	} else {
                	System.out.println("Draw ���þȵ�");
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
                System.out.println("Draw ����");
            	} else {
                	System.out.println("Draw ���þȵ�");
                }
            }
            
        });

    }
    
    public void initialize(GPolHandler handler) { 
        this.polHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(handler != null) {
                handler.setMovingMode(false);
                System.out.println("Draw ����");
            	} else {
                	System.out.println("Draw ���þȵ�");
                }
            }
            
        });

    }
    
    public void initialize(GOvalHandler handler) { 
        this.ovalHandler = handler;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(handler != null) {
                handler.setMovingMode(false);
                System.out.println("Draw ����");
            	} else {
                	System.out.println("Draw ���þȵ�");
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
                    handler.setMovingMode(false);
                    System.out.println("Draw ����");
                } else {
                    System.out.println("Draw ���þȵ�");
                }
            }
        });
    }
}