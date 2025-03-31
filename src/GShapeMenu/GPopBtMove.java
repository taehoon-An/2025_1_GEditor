package GShapeMenu;

import javax.swing.JMenuItem;

import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTextHandler;
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

    public GPopBtMove() {
        super("Move");
        

    }
    
    public void initialize(GRecHandler handler) {
    	this.recHandler = handler;
    	this.addActionListener(new ActionListener() { //���� Move�޴����õ�����
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode Ȱ��ȭ
                System.out.println("Move��� ����");
            	}
            }
        }); 
    }
    
    public void initialize(GTriHandler handler) {
    	this.triHandler = handler;
    	this.addActionListener(new ActionListener() { //���� Move�޴����õ�����
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode Ȱ��ȭ
                System.out.println("Move��� ����");
            	}
            }
        }); 
    }
    
    public void initialize(GPolHandler handler) {
    	this.polHandler = handler;
    	this.addActionListener(new ActionListener() { //���� Move�޴����õ�����
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode Ȱ��ȭ
                System.out.println("Move��� ����");
            	}
            }
        }); 
    }
    
    public void initialize(GOvalHandler handler) {
    	this.ovalHandler = handler;
    	this.addActionListener(new ActionListener() { //���� Move�޴����õ�����
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler != null) {
                handler.setMovingMode(true);//MovingMode Ȱ��ȭ
                System.out.println("Move��� ����");
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
                    System.out.println("Move��� ����");
                }
            }
        });
    }
}