package GHandler;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GButton.GTriButton;
import GEditor.GDrawingPanel;

public class GTriHandler extends MouseAdapter {
    private GDrawingPanel panel;
    private GTriButton triButton;
    private Point startPoint, currentPoint;
    private int selectedTriangleIndex = -1;
    private boolean isMoving = false;
    public Polygon tempTriangle;

    public GTriHandler(GDrawingPanel panel) {
        this.panel = panel;
    }

    public void setTriButton(GTriButton triButton) {
        this.triButton = triButton;
    }
    
    public void setMovingMode(boolean mode) {
        this.isMoving = mode;
        System.out.println("Tri Moving Mode: " + mode);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (triButton != null && triButton.isSelected()) {
            startPoint = e.getPoint();
            ArrayList<Polygon> triangles = panel.getTriangles();

            if (isMoving) {
                selectedTriangleIndex = -1; //인덱스 초기화
                for (int i = 0; i < triangles.size(); i++) { //저장된 array에서 선택된 삼각형 인덱스 찾는 로직
                    Polygon t = triangles.get(i);
                    if (t.contains(startPoint)) {//만약 커서 좌표가 이 인덱스의 삼각형 안에 있었다면
                        selectedTriangleIndex = i; //인덱스 설정
                        currentPoint = startPoint;
                        System.out.println("Selected Triangle Index: " + selectedTriangleIndex);
                        break;
                    }
                }
            } else {
                // Not in moving mode, prepare for drawing
                tempTriangle = new Polygon();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (triButton != null && triButton.isSelected()) {//triangle버튼 선택되어있을떄
            currentPoint = e.getPoint();//

            if (isMoving && selectedTriangleIndex != -1) {//만약 move모드면
                ArrayList<Polygon> triangles = panel.getTriangles();//getter
                Polygon selectedTriangle = triangles.get(selectedTriangleIndex);//선택한 index삼각형 정보 저장
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 현재 삼각형의 각 꼭잣점 x,y좌표를 저장해 실시간으로 생성하기 위한 준비
                int[] newXPoints = new int[selectedTriangle.npoints];
                int[] newYPoints = new int[selectedTriangle.npoints];
                //각 꼭짓점에 저장된 x,y좌표 움직인 좌표를 더해 움직인 곳에서의 좌표로 새로저장
                for (int i = 0; i < selectedTriangle.npoints; i++) {
                    newXPoints[i] = selectedTriangle.xpoints[i] + dx;
                    newYPoints[i] = selectedTriangle.ypoints[i] + dy;
                }
                //저장한 새 좌표에 대해서 triangle좌표를 추가하기
                Polygon movedTriangle = new Polygon(newXPoints, newYPoints, selectedTriangle.npoints);
                
                // 새롭게 그리는 삼각형 정보를 선택한 인덱스 삼각형에 계속 저장
                triangles.set(selectedTriangleIndex, movedTriangle);
                //다시 drag할때 똑같이 계산하기 위해서 startPoint를 현재 좌표로 설정
                startPoint = currentPoint;
                panel.repaint();
            } else if (!isMoving) {
                // Drawing mode (꼭짓점 좌표 세개)
                int x1 = startPoint.x;
                int y1 = currentPoint.y;

                int x2 = (startPoint.x + currentPoint.x) / 2;
                int y2 = startPoint.y;

                int x3 = currentPoint.x;
                int y3 = currentPoint.y;

                
                tempTriangle.reset();
                tempTriangle.addPoint(x1, y1);
                tempTriangle.addPoint(x2, y2);
                tempTriangle.addPoint(x3, y3);

                panel.setTempTriangle(tempTriangle);
                panel.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (triButton != null && triButton.isSelected() && !isMoving) {
            if (tempTriangle != null && tempTriangle.npoints == 3) {//꼭짓점 세개이고, 안비어있을떄
                System.out.println("Triangle add 완");
                panel.addTriangle(new Polygon(tempTriangle.xpoints, tempTriangle.ypoints, 3));//triangle추가
            }
        }
        
        tempTriangle = null;
        selectedTriangleIndex = -1;
        panel.repaint();
    }
}