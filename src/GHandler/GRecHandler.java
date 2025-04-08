package GHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import GButton.GRecButton;
import GEditor.GDrawingPanel;
import GKindOfShapes.GRectangle;

public class GRecHandler implements MouseListener, MouseMotionListener  {
    private GDrawingPanel panel;
    private GRecButton recButton;
    private Point startPoint, currentPoint;
    private int selectedRectangleIndex = -1;
    private boolean isMoving = false;
    private boolean isDrawing = false;
    public GRectangle tempRectangle;

    public GRecHandler(GDrawingPanel panel) {
        this.panel = panel;
    }

    public void setRecButton(GRecButton recButton) {
        this.recButton = recButton;
    }
    
    public void setMovingMode(boolean mode) { //모드활성화 유무
        this.isMoving = mode;
        System.out.println("Moving Mode: " + mode);
    }
    
    public void setDrawingMode(boolean mode) {
    	this.isDrawing = mode;
    	System.out.println("Drawing Mode: " + mode);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (recButton != null && recButton.isSelected()) { //rec버튼 눌려있을때
            startPoint = e.getPoint(); //처음 찍었을때 좌표 저장
            ArrayList<GRectangle> rectangles = panel.getRectangles(); //getter 

            if (isMoving) { //move모드일떄
                selectedRectangleIndex = -1; //index 초기화 후 선택된 rectangles 찾는 로직
                for (int i = 0; i < rectangles.size(); i++) {
                    GRectangle r = rectangles.get(i);
                    if (r.contains(startPoint)) { //사각형 그림 안에 startpoint 좌표가있는지(커서가 안에 있는지)
                        selectedRectangleIndex = i; //인덱스만 저장
                        currentPoint = startPoint;
                        System.out.println("Selected Rectangle Index: " + selectedRectangleIndex);
                        break;
                    }
                }
            } else {
                // Draw Mode일때 Pressed
            	currentPoint = startPoint;
                tempRectangle = new GRectangle(startPoint.x, startPoint.y, 0, 0);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	Graphics2D g2d = (Graphics2D) panel.getGraphics();
    	g2d.setColor(Color.BLUE); //drag할때 특정 색깔지정
        g2d.setXORMode(panel.getBackground());
        if (recButton != null && recButton.isSelected()) {

            if (isMoving && selectedRectangleIndex != -1) {
            	currentPoint = e.getPoint();
                //존재하는 rectangle 움직이기
                ArrayList<GRectangle> rectangles = panel.getRectangles(); //panel에 저장되어있는 Rectangle어레이 가져오기
                GRectangle selectedRectangle = rectangles.get(selectedRectangleIndex);//아까 저장되어있던 index에 의해 사각형 가져오기
                //set되어있던 array의 rectangle실시간으로 그려서 xor모드에 의해 잔상 삭제
                selectedRectangle.setPoint(selectedRectangle.x,selectedRectangle.y,selectedRectangle.width,selectedRectangle.height);
                selectedRectangle.recDraw(g2d);
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 움직일때만 새로운 rectangle을 만들어 실시간 움직이는 사각형 그리기 위한 사각형
                GRectangle movedRectangle = new GRectangle(
                    selectedRectangle.x + dx, 
                    selectedRectangle.y + dy, 
                    selectedRectangle.width, 
                    selectedRectangle.height
                );
                movedRectangle.setPoint(movedRectangle.x, movedRectangle.y, movedRectangle.width, movedRectangle.height);
                movedRectangle.recDraw(g2d);
                
                rectangles.set(selectedRectangleIndex, movedRectangle);//실시간으로 움직이는 rectangle 정보 꺼냈던 array인덱스에 저장
                
                startPoint = currentPoint;
            } else if (!isMoving) {
                // Drawing mode 다시그려 잔상 삭제
                tempRectangle.recDraw(g2d);
                
                // 새 좌표 계산
                currentPoint = e.getPoint();
                int x = Math.min(startPoint.x, currentPoint.x);
                int y = Math.min(startPoint.y, currentPoint.y);
                int width = Math.abs(startPoint.x - currentPoint.x);
                int height = Math.abs(startPoint.y - currentPoint.y);

                // 새 사각형 생성 및 그리기
                tempRectangle.setPoint(x, y, width, height);
                tempRectangle.recDraw(g2d);
                // tempRectangle을 panel에 설정 (release 이벤트에서 사용)
   //             tempRectangle.setRectangle(tempRectangle);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
 //   	System.out.print(tempRectangle.height);
        if (recButton != null && recButton.isSelected() && !isMoving) {
            if (tempRectangle != null && tempRectangle.width > 0 && tempRectangle.height > 0) {
                System.out.println("add Rectangle");
                panel.addRectangle(tempRectangle);
            }
        }
        
        tempRectangle = null;
        selectedRectangleIndex = -1;
        panel.repaint(); //없으면 지정한 색깔이 제대로 paint되지 않음. 
    }

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}