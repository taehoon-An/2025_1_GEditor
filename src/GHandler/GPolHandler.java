package GHandler;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GButton.GPolButton;
import GButton.GTriButton;
import GEditor.GDrawingPanel;

public class GPolHandler extends MouseAdapter {
	private GDrawingPanel panel;
    private GPolButton polButton;
    private Point startPoint, currentPoint;
    private int selectedPolygonIndex = -1;
    private boolean isMoving = false;
    public Polygon tempPolygon;

    public GPolHandler(GDrawingPanel panel) {
        this.panel = panel;
    }

    public void setPolButton(GPolButton polButton) {
        this.polButton = polButton;
    }
    
    public void setMovingMode(boolean mode) {
        this.isMoving = mode;
        System.out.println("Pol Moving Mode: " + mode);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (polButton != null && polButton.isSelected()) {
            startPoint = e.getPoint();
            ArrayList<Polygon> polygons = panel.getPolygons();

            if (isMoving) {
                selectedPolygonIndex = -1; //인덱스 초기화
                for (int i = 0; i < polygons.size(); i++) { //저장된 array에서 선택된 삼각형 인덱스 찾는 로직
                    Polygon p = polygons.get(i);
                    if (p.contains(startPoint)) {//만약 커서 좌표가 이 인덱스의 삼각형 안에 있었다면
                        selectedPolygonIndex = i; //인덱스 설정
                        currentPoint = startPoint;
                        System.out.println("Selected Polygons Index: " + selectedPolygonIndex);
                        break;
                    }
                }
            } else {
                // Not in moving mode, prepare for drawing
                tempPolygon = new Polygon();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (polButton != null && polButton.isSelected()) {//polygon버튼 선택되어있을떄
            currentPoint = e.getPoint();//

            if (isMoving && selectedPolygonIndex != -1) {//만약 move모드면
                ArrayList<Polygon> polygons = panel.getPolygons();//getter
                Polygon selectedPolygon = polygons.get(selectedPolygonIndex);//선택한 index삼각형 정보 저장
                
                int dx = currentPoint.x - startPoint.x;
                int dy = currentPoint.y - startPoint.y;

                // 계속 새롭게 이 좌표로 삼각형 그리기
                int[] newXPoints = new int[selectedPolygon.npoints];
                int[] newYPoints = new int[selectedPolygon.npoints];
                
                for (int i = 0; i < selectedPolygon.npoints; i++) {
                    newXPoints[i] = selectedPolygon.xpoints[i] + dx;
                    newYPoints[i] = selectedPolygon.ypoints[i] + dy;
                }
                
                Polygon movedPolygon = new Polygon(newXPoints, newYPoints, selectedPolygon.npoints);
                
                // 새롭게 그리는 삼각형 정보를 선택한 인덱스 삼각형에 계속 저장
                polygons.set(selectedPolygonIndex, movedPolygon);
                
                startPoint = currentPoint;
                panel.repaint();
            } else if (!isMoving) {
                // Drawing mode
                int x1 = startPoint.x;
                int y1 = currentPoint.y;

                int x2 = (startPoint.x + currentPoint.x) / 2;
                int y2 = startPoint.y;

                int x3 = currentPoint.x;
                int y3 = currentPoint.y;

                tempPolygon.reset();
                tempPolygon.addPoint(x1, y1);
                tempPolygon.addPoint(x2, y2);
                tempPolygon.addPoint(x3, y3);

                panel.setTempPolygon(tempPolygon);
                panel.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (polButton != null && polButton.isSelected() && !isMoving) {
            if (tempPolygon != null && tempPolygon.npoints == 3) {
                System.out.println("Polygon add 완");
                panel.addPolygon(new Polygon(tempPolygon.xpoints, tempPolygon.ypoints, 3));
            }
        }
        
        tempPolygon = null;
        selectedPolygonIndex = -1;
        panel.repaint();
    }
}

