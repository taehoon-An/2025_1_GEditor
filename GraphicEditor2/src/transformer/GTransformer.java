package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shapes.GRectangle;
import shapes.GShape;

//√ﬂªÛ»≠ Device
public interface GTransformer {
    public void start(Graphics2D g, int x, int y);
    public void drag(Graphics2D g, int x, int y);
    public GShape finish(Graphics2D g, int x, int y);
}