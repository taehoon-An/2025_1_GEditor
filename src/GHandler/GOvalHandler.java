package GHandler;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GButton.GOvalButton;
import GButton.GRecButton;
import GEditor.GDrawingPanel;

public class GOvalHandler extends MouseAdapter {
	private GDrawingPanel panel;
    private GOvalButton ovalButton;
    private Point startPoint, currentPoint;
    private int selectedOvalIndex = -1;
    private boolean isMoving = false;
    public Rectangle tempOval;

    public GOvalHandler(GDrawingPanel panel) {
        this.panel = panel;
    }

    public void setOvalButton(GOvalButton ovalButton) {
        this.ovalButton = ovalButton;
    }
    
    public void setMovingMode(boolean mode) { //모드활성화 유무
        this.isMoving = mode;
        System.out.println("Moving Mode: " + mode);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (ovalButton != null && ovalButton.isSelected()) { //rec버튼 눌려있을때
            startPoint = e.getPoint(); //처음 찍었을때 좌표 저장
            ArrayList<Rectangle> ovals = panel.getOvals(); //getter 

            if (isMoving) { //move모드일떄
                selectedOvalIndex = -1; //index 초기화 후 선택된 rectangles 찾는 로직
                for (int i = 0; i < ovals.size(); i++) {
                    Rectangle r = ovals.get(i);
                    if (r.contains(startPoint)) { //사각형 그림 안에 startpoint 좌표가있는지(커서가 안에 있는지)
                        selectedOvalIndex = i; //인덱스만 저장
                        currentPoint = startPoint;
                        System.out.println("Selected Oval Index: " + selectedOvalIndex);
                        break;
                    }
                }
            } else {
                // Not in moving mode, prepare for drawing
                tempOval = new Rectangle(startPoint.x, startPoint.y, 0, 0);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (ovalButton != null && ovalButton.isSelected()) {
            currentPoint = e.getPoint();

            if (isMoving && selectedOvalIndex != -1) {
                //존재하는 rectangle 움직이기
                ArrayList<Rectangle> ovals = panel.getOvals(); //panel에 저장되어있는 Rectangle어레이 가져오기
                Rectangle selectedOval = ovals.get(selectedOvalIndex);//아까 저장되어있던 index에 의해 사각형 가져오기
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 움직일때만 새로운 rectangle을 만들어 실시간 움직이는 사각형 그리기 위한 사각형
                Rectangle movedOval = new Rectangle(
                    selectedOval.x + dx, 
                    selectedOval.y + dy, 
                    selectedOval.width, 
                    selectedOval.height
                );
                
                
                ovals.set(selectedOvalIndex, movedOval);//실시간으로 움직이는 rectangle 정보 꺼냈던 array인덱스에 저장
                
                startPoint = currentPoint;
                panel.repaint();
            } else if (!isMoving) {
                // Drawing mode
                int x = Math.min(startPoint.x, currentPoint.x);
                int y = Math.min(startPoint.y, currentPoint.y);
                int width = Math.abs(startPoint.x - currentPoint.x);
                int height = Math.abs(startPoint.y - currentPoint.y);

                tempOval = new Rectangle(x, y, width, height);
                panel.setTempOval(tempOval);
                panel.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (ovalButton != null && ovalButton.isSelected() && !isMoving) {
            if (tempOval != null && tempOval.width > 0 && tempOval.height > 0) {
                System.out.println("add Oval");
                panel.addOval(new Rectangle(tempOval));
            }
        }
        
        tempOval = null;
        selectedOvalIndex = -1;
        panel.repaint();
    }
}

