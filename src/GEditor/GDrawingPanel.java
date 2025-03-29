package GEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;
import GButton.GRecButton;
import GButton.GTriButton;
import GHandler.GRecHandler;
import GHandler.GTriHandler;

public class GDrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Polygon> triangles;
    private GRecButton recButton;
    private GRecHandler recHandler;
    private GTriButton triButton;
    private GTriHandler triHandler;
    Rectangle tempRectangle;
    Polygon tempTriangle;

    public GDrawingPanel() {
        this.rectangles = new ArrayList<>();
        this.triangles = new ArrayList<>();
    }
    
    public void setRecHandler(GRecHandler handler) {
    	this.recHandler = handler;
    }
    
    public void setTriHandler(GTriHandler handler) {
    	this.triHandler = handler;
    }

    public void setRecButton(GRecButton recButton) {
        this.recButton = recButton; //recbutton 눌리면 panel내부의 변수에 정보 저장
        if (recHandler != null) { //handler 사용중이면
            recHandler.setRecButton(recButton); //GHandler 객체 내부의 recbutton 변수에도 저장
        }
    }
    
    public void setTriButton(GTriButton triButton) {
        this.triButton = triButton; //recbutton 눌리면 panel내부의 변수에 정보 저장
        if (triHandler != null) { //handler 사용중이면
            triHandler.setTriButton(triButton); //GHandler 객체 내부의 recbutton 변수에도 저장
        }
    }

    public void initialize() {
        if(recHandler != null) {
        	this.addMouseListener(recHandler);
        	this.addMouseMotionListener(recHandler);
        } 
        if(triHandler != null) {
            this.addMouseListener(triHandler);
            this.addMouseMotionListener(triHandler);
        }
    }
    
    

    public void addRectangle(Rectangle r) {
    	System.out.println("add Rec Complete");
        rectangles.add(r);//Handler에서 작동하는 rec정보 여기 어레이에 저장
        repaint();
    }
    
    public void addTriangle(Polygon t) {
    	System.out.println("add Tri Complete");
        triangles.add(t);//Handler에서 작동하는 rec정보 여기 어레이에 저장
        repaint();
    }
    
    public void setTempRectangle(Rectangle tempRectangle) {
        this.tempRectangle = tempRectangle;
    }
    
    public void setTempTriangle(Polygon tempTriangle) {
        this.tempTriangle = tempTriangle;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles; //move모드에서 쓰기 위한 정보
    }
    
    public ArrayList<Polygon> getTriangles() {
        return triangles; //move모드에서 쓰기 위한 정보
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if(recButton != null) {
        	recDraw(g);
        }
        if(triButton != null) {
        	triDraw(g);
        }
    }

    public void recDraw(Graphics g) { //현재 내부에 저장되어있는 Rectangle Array 정보를 paint하는 메서드
        g.setColor(Color.RED);
        for (Rectangle r : rectangles) {
            g.drawRect(r.x, r.y, r.width, r.height);
        }
        
        if (recHandler.tempRectangle != null) {//실시간 draw 출력
            g.setColor(Color.BLUE);
            g.drawRect(tempRectangle.x, tempRectangle.y
            		,tempRectangle.width, tempRectangle.height);
        }
        
    }
        
        public void triDraw(Graphics g) { //현재 내부에 저장되어있는 Triangle Array 정보를 paint하는 메서드
            g.setColor(Color.RED);
            for (Polygon p : triangles) {
                g.drawPolygon(p);
            }
            
            if (triHandler.tempTriangle != null) {//실시간 draw 출력
                g.setColor(Color.BLUE);
                g.drawPolygon(tempTriangle);
            }
        

    
        }
}