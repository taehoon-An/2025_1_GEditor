package GHandler;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import GButton.GRecButton;
import GKindOfShapes.GRectangle;

public class GTransformer {
	GRectangle rectangle;
	private GRecButton recButton;
	private Point startPoint, currentPoint;
	private int selectedRectangleIndex = -1;
	public GRectangle tempRectangle;
	private boolean isMoving = false;
    private boolean isDrawing = false;
    private ArrayList<GRectangle> rectangles;
	
	
	public void start(Graphics2D g2d, Point point, ArrayList<GRectangle> rectangles) {
		this.rectangles = rectangles;
		if (recButton != null && recButton.isSelected()) { //rec버튼 눌려있을때
            startPoint = point; //처음 찍었을때 좌표 저장

            if (isMoving) { //move모드일떄
                selectedRectangleIndex = -1; //index 초기화 후 선택된 rectangles 찾는 로직
                for (int i = 0; i < this.rectangles.size(); i++) {
                    GRectangle r = this.rectangles.get(i);
                    if (r.contains(startPoint)) { //사각형 그림 안에 startpoint 좌표가있는지(커서가 안에 있는지)
                        selectedRectangleIndex = i; //인덱스만 저장
                        tempRectangle = this.rectangles.get(i);
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
	
	public void drag(Graphics2D g2d, Point point) {
		if (recButton != null && recButton.isSelected()) {

            if (isMoving && selectedRectangleIndex != -1) {
            	currentPoint = point;
                //존재하는 rectangle 움직이기
//                GRectangle selectedRectangle = rectangles.get(selectedRectangleIndex);//아까 저장되어있던 index에 의해 사각형 가져오기
                //set되어있던 array의 rectangle실시간으로 그려서 xor모드에 의해 잔상 삭제
                tempRectangle.setPoint(tempRectangle.x,tempRectangle.y,tempRectangle.width,tempRectangle.height);
                tempRectangle.recDraw(g2d);
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 움직일때만 새로운 rectangle을 만들어 실시간 움직이는 사각형 그리기 위한 사각형
                tempRectangle.setPoint(
                		tempRectangle.x + dx, 
                		tempRectangle.y + dy, 
                		tempRectangle.width, 
                		tempRectangle.height
                );
                tempRectangle.recDraw(g2d);
                
                rectangles.set(selectedRectangleIndex, tempRectangle);//실시간으로 움직이는 rectangle 정보 꺼냈던 array인덱스에 저장
                
                startPoint = currentPoint;
            } else if (!isMoving) {
                // Drawing mode 다시그려 잔상 삭제
                tempRectangle.recDraw(g2d);
                
                // 새 좌표 계산
                currentPoint = point;
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
	
	public void finish() {
		if (recButton != null && recButton.isSelected() && !isMoving) {
            if (tempRectangle != null && tempRectangle.width > 0 && tempRectangle.height > 0) {
                System.out.println("add Rectangle");
                rectangles.add(tempRectangle);
            }
        }
        
        tempRectangle = null;
        selectedRectangleIndex = -1;
	}

	public void setRecButton(GRecButton recButton) {
        this.recButton = recButton;
    }
	
	public GRecButton getRecButton() {
		return this.recButton;
	}
    
    public void setMovingMode(boolean mode) { //모드활성화 유무
        this.isMoving = mode;
        System.out.println("Moving Mode: " + mode);
    }
    
    public boolean getMovingMode() {
    	return this.isMoving;
    }
    
    public void setDrawingMode(boolean mode) {
    	this.isDrawing = mode;
    	System.out.println("Drawing Mode: " + mode);
    }
    
    public ArrayList<GRectangle> getRectangles() {
    	return this.rectangles;
    }
    
    public GRectangle getTempRectangle() {
    	return this.tempRectangle;
    }

	

}

