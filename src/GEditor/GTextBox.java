package GEditor;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GTextBox {
    private int x, y;
    private String text;
    private Font font;
    
    public GTextBox(int x, int y, String text, Font font) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = font;
    }
    
    public void draw(Graphics g) {
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x - 5, y - 20, (int)(text.length() * 8), 25);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Font getFont() {
        return font;
    }
}