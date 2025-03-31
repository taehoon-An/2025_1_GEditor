package GEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

import GButton.GOvalButton;
import GButton.GPolButton;
import GButton.GRecButton;
import GButton.GTextButton;
import GButton.GTriButton;
import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTextHandler;
import GHandler.GTriHandler;

public class GDrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Polygon> triangles;
    private ArrayList<Polygon> polygons;
    private ArrayList<Rectangle> ovals;
    private ArrayList<GTextBox> textBoxes;
    private GRecButton recButton;
    private GRecHandler recHandler;
    private GTriButton triButton;
    private GTriHandler triHandler;
    private GPolButton polButton;
    private GPolHandler polHandler;
    private GOvalButton ovalButton;
    private GOvalHandler ovalHandler;
    private GTextButton textButton;
    private GTextHandler textHandler;
    
    Rectangle tempRectangle;
    Polygon tempTriangle;
    Polygon tempPolygon;
    Rectangle tempOval;
    GTextBox tempTextBox;

    public GDrawingPanel() {
        this.rectangles = new ArrayList<>();
        this.triangles = new ArrayList<>();
        this.polygons = new ArrayList<>();
        this.ovals = new ArrayList<>();
        this.textBoxes = new ArrayList<>();
    }
    
    public void setRecHandler(GRecHandler handler) {
    	this.recHandler = handler;
    }
    
    public void setTriHandler(GTriHandler handler) {
    	this.triHandler = handler;
    }
    
    public void setPolHandler(GPolHandler handler) {
    	this.polHandler = handler;
    }
    
    public void setOvalHandler(GOvalHandler handler) {
    	this.ovalHandler = handler;
    }
    
    public void setTextHandler(GTextHandler handler) {
    	this.textHandler = handler;
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
    
    public void setPolButton(GPolButton polButton) {
    	this.polButton = polButton;
    	if (polHandler != null) {
    		polHandler.setPolButton(polButton);
    	}
    }
    
    public void setOvalButton(GOvalButton ovalButton) {
    	this.ovalButton = ovalButton;
    	if (ovalHandler != null) {
    		ovalHandler.setOvalButton(ovalButton);
    	}
    }
    
    public void setTextButton(GTextButton textButton) {
        this.textButton = textButton;
        if (textHandler != null) {
        	textHandler.setTextButton(textButton);
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
        if(polHandler != null) {
        	this.addMouseListener(polHandler);
            this.addMouseMotionListener(polHandler);
        }
        if(ovalHandler != null) {
        	this.addMouseListener(ovalHandler);
            this.addMouseMotionListener(ovalHandler);
        }
        if(textHandler != null) {
        	this.addMouseListener(textHandler);
        	this.addMouseMotionListener(textHandler);
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
    
    public void addPolygon(Polygon t) {
    	System.out.println("add Pol Complete");
    	polygons.add(t);
    	repaint();
    }
    
    public void addOval(Rectangle o) {
    	System.out.println("add Oval Complete");
    	ovals.add(o);
    	repaint();
    }
    
    public void addTextBox(GTextBox tB) {
    	System.out.println("add Text Complete");
        textBoxes.add(tB);
        repaint();
    }
    
    
    public void setTempRectangle(Rectangle tempRectangle) {
        this.tempRectangle = tempRectangle;
    }
    
    public void setTempTriangle(Polygon tempTriangle) {
        this.tempTriangle = tempTriangle;
    }
    
    public void setTempPolygon(Polygon tempPolygon) {
    	this.tempPolygon = tempPolygon;
    }
    
    public void setTempOval(Rectangle tempOval) {
    	this.tempOval = tempOval;
    }
    
    public void setTempTextBox(GTextBox textBox) {
        this.tempTextBox = textBox;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles; //move모드에서 쓰기 위한 정보
    }
    
    public ArrayList<Polygon> getTriangles() {
        return triangles; //move모드에서 쓰기 위한 정보
    }
    
    public ArrayList<Polygon> getPolygons() {
    	return polygons;
    }
    
    public ArrayList<Rectangle> getOvals() {
    	return ovals;
    }
    
    public ArrayList<GTextBox> getTextBoxes() {
        return textBoxes;
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
        if(polButton != null) {
        	polDraw(g);
        }
        if(ovalButton != null) {
        	ovalDraw(g);
        }
        if(textButton != null) {
        	textDraw(g);
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
    
    public void polDraw(Graphics g) { // 현재 내부에 저장되어있는 Polygon Array 정보를 paint하는 메서드
    	g.setColor(Color.RED);
        for (Polygon p : polygons) {
            g.drawPolygon(p);
        }
        
        if (polHandler.tempPolygon != null) {//실시간 draw 출력
            g.setColor(Color.BLUE);
            g.drawPolygon(tempPolygon);
        }
    }
    
    public void ovalDraw(Graphics g) {
    	g.setColor(Color.RED);
        for (Rectangle o : ovals) {
            g.drawOval(o.x,o.y,o.width,o.height);
        }
        
        if (ovalHandler.tempOval != null) {//실시간 draw 출력
            g.setColor(Color.BLUE);
            g.drawOval(tempOval.x,tempOval.y,tempOval.width,tempOval.height);
        }
    }
    
    public void textDraw(Graphics g) {
    	g.setColor(Color.BLACK);
    	 for (GTextBox textBox : textBoxes) {
             textBox.draw(g);
         }
         
         // 임시 TextBox 그리기
         if (tempTextBox != null) {
             tempTextBox.draw(g);
         }
    }
}