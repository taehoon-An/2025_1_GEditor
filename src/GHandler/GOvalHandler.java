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

import GButton.GOvalButton;
import GButton.GRecButton;
import GEditor.GDrawingPanel;

public class GOvalHandler implements MouseListener, MouseMotionListener {
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
        if (ovalButton != null && ovalButton.isSelected()) { //oval버튼 눌려있을때
            startPoint = e.getPoint(); //처음 찍었을때 좌표 저장
            ArrayList<Rectangle> ovals = panel.getOvals(); //getter 

            if (isMoving) { //move모드일떄
                selectedOvalIndex = -1; //index 초기화 후 선택된 rectangles 찾는 로직
                for (int i = 0; i < ovals.size(); i++) {
                    Rectangle o = ovals.get(i);
                    if (o.contains(startPoint)) { //oval 그림 안에 startpoint 좌표가있는지(커서가 안에 있는지)
                        selectedOvalIndex = i; //인덱스만 저장
                        currentPoint = startPoint;
                        System.out.println("Selected Oval Index: " + selectedOvalIndex);
                        break;
                    }
                }
            } else {
                // Move모드 아닌 Draw모드일땐 그리기 준비
            	currentPoint = startPoint; //Xor모드로 인해 다시 그려 잔상 없애기 위한 좌표
                tempOval = new Rectangle(startPoint.x, startPoint.y, 0, 0);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	Graphics2D g2d = (Graphics2D) panel.getGraphics();
    	g2d.setColor(Color.BLUE); //drag할때 특정 색깔지정
        g2d.setXORMode(panel.getBackground());
        if (ovalButton != null && ovalButton.isSelected()) {

            if (isMoving && selectedOvalIndex != -1) {
            	currentPoint = e.getPoint();
                //존재하는 rectangle 움직이기
                ArrayList<Rectangle> ovals = panel.getOvals(); //panel에 저장되어있는 Oval어레이 가져오기
                Rectangle selectedOval = ovals.get(selectedOvalIndex);//아까 저장되어있던 index에 의해 oval 가져오기
                //이동하기 전 그림다시 그려서 xor모드에 의해 삭제
                g2d.drawOval(selectedOval.x, selectedOval.y, selectedOval.width, selectedOval.height);
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 움직일때만 새로운 Oval을 만들어 실시간 움직이는 사각형 그리기 위한 사각형
                Rectangle movedOval = new Rectangle(
                    selectedOval.x + dx, 
                    selectedOval.y + dy, 
                    selectedOval.width, 
                    selectedOval.height
                );
                g2d.drawOval(movedOval.x, movedOval.y, movedOval.width, movedOval.height);
                
                ovals.set(selectedOvalIndex, movedOval);//실시간으로 움직이는 Oval 정보 꺼냈던 array인덱스에 저장
                
                startPoint = currentPoint;
            } else if (!isMoving) {
                // Drawing mode 다시그려 잔상 삭제
            	g2d.drawOval(tempOval.x, tempOval.y, tempOval.width, tempOval.height);
            	
            	//새좌표 계산
            	currentPoint = e.getPoint();
                int x = Math.min(startPoint.x, currentPoint.x);
                int y = Math.min(startPoint.y, currentPoint.y);
                int width = Math.abs(startPoint.x - currentPoint.x);
                int height = Math.abs(startPoint.y - currentPoint.y);

                // 새 Oval생성 및 그리기
                tempOval = new Rectangle(x, y, width, height);
                g2d.drawOval(tempOval.x, tempOval.y, tempOval.width, tempOval.height);
                
                //tempOval을 panel에 설정
                panel.setTempOval(tempOval);
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
        panel.repaint(); //있어야 release했을때 색깔 제대로 나옴
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

