package GEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics2D;

import GButton.GOvalButton;
import GButton.GPolButton;
import GButton.GRecButton;
import GButton.GTextButton;
import GButton.GTriButton;
import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTextHandler;
import GHandler.GTransformer;
import GHandler.GTriHandler;
import GKindOfShapes.GRectangle;

//추가되는 Array와 이 Panel에 대한 Graphics 관리
public class GDrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<GRectangle> rectangles;
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
    
    private GTransformer transformer;
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
        this.transformer = new GTransformer();
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
            transformer.setRecButton(recButton); //GHandler 객체 내부의 recbutton 변수에도 저장
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
    
    public void addTriangle(Polygon t) {
    	System.out.println("add Tri Complete");
        triangles.add(t);//Handler에서 작동하는 rec정보 여기 어레이에 저장
    }
    
    public void addPolygon(Polygon t) {
    	System.out.println("add Pol Complete");
    	polygons.add(t);
    }
    
    public void addOval(Rectangle o) {
    	System.out.println("add Oval Complete");
    	ovals.add(o);
    }
    
    public void addTextBox(GTextBox tB) {
    	System.out.println("add Text Complete");
        textBoxes.add(tB);
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

    public ArrayList<GRectangle> getRectangles() {
        return rectangles; //move모드에서 쓰기 위한 정보
    }
    
    public void setRectangles(ArrayList<GRectangle> rectangles) {
    	this.rectangles = rectangles;
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
        super.paintComponent(g);  //paintComponent에 XorMode를 사용하지 않으면 겹칠 때 사라지지 않음
        if(recButton != null) {
        	for (GRectangle r : rectangles) {
        		r.setPoint(r.x, r.y, r.width, r.height);
                r.recDraw((Graphics2D)g);
            }
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
        
    public void triDraw(Graphics g) { //현재 내부에 저장되어있는 Triangle Array 정보를 paint하는 메서드
        g.setColor(Color.BLACK);
        for (Polygon p : triangles) {
            g.drawPolygon(p);
        }
      
    }
    
    public void polDraw(Graphics g) { // 현재 내부에 저장되어있는 Polygon Array 정보를 paint하는 메서드
    	g.setColor(Color.BLACK);
        for (Polygon p : polygons) {
            g.drawPolygon(p);
        }
    }
    
    public void ovalDraw(Graphics g) {
    	g.setColor(Color.BLACK);
        for (Rectangle o : ovals) {
            g.drawOval(o.x,o.y,o.width,o.height);
        }
        
    }
    
    public void textDraw(Graphics g) {
    	g.setColor(Color.BLACK);
    	 for (GTextBox textBox : textBoxes) {
             textBox.draw(g);
         }
         
         // 임시 TextBox 그리기
         if (textHandler.tempTextBox != null) {
             tempTextBox.draw(g);
         }
    }

	public GTransformer getTransformer() {
		return this.transformer;
	}
}