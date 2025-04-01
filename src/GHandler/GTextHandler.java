package GHandler;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import GButton.GTextButton;
import GEditor.GDrawingPanel;
import GEditor.GTextBox;

public class GTextHandler implements MouseListener, MouseMotionListener {
	private GDrawingPanel panel;
    private GTextButton textButton;
    private Point startPoint, currentPoint;
    private int selectedTextBoxIndex = -1;
    private boolean isMoving = false;
    private boolean isTyping = false;
    public GTextBox tempTextBox;
    private StringBuilder currentText = new StringBuilder();//각 String최적화를 위한 class활용
    private KeyAdapter textInputAdapter;

    public GTextHandler(GDrawingPanel panel) {
        this.panel = panel;
        
        // 키 입력을 위한 어댑터 생성
        textInputAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (isTyping && tempTextBox != null) {
                    char c = e.getKeyChar();
                    if (c == KeyEvent.VK_ENTER) {
                        // Enter 키를 누르면 텍스트 입력 완료
                        finishTextInput();
                    } else if (c == KeyEvent.VK_BACK_SPACE) {
                        // 백스페이스 처리
                        if (currentText.length() > 0) {
                            currentText.deleteCharAt(currentText.length() - 1);
                            tempTextBox.setText(currentText.toString());
                            panel.repaint();
                        }
                    } else {
                        // 문자를 텍스트에 추가
                        currentText.append(c);
                        tempTextBox.setText(currentText.toString());
                        panel.repaint();
                    }
                }
            }
        };
    }

    public void setTextButton(GTextButton textButton) {
        this.textButton = textButton;
    }
    
    public void setMovingMode(boolean mode) {
        this.isMoving = mode;
        System.out.println("Text Moving Mode: " + mode);
    }
    
    public void setDrawingMode(boolean mode) {
        this.isMoving = !mode;
        System.out.println("Text Drawing Mode: " + mode);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (textButton != null && textButton.isSelected()) {
            startPoint = e.getPoint();
            ArrayList<GTextBox> textBoxes = panel.getTextBoxes();

            if (isMoving) {
                selectedTextBoxIndex = -1;
                for (int i = 0; i < textBoxes.size(); i++) {
                    GTextBox t = textBoxes.get(i);
                    if (t.getBounds().contains(startPoint)) {
                        selectedTextBoxIndex = i;
                        currentPoint = startPoint;
                        System.out.println("Selected TextBox Index: " + selectedTextBoxIndex);
                        break;
                    }
                }
            } else {
                // 그리기 모드 - 새 텍스트 박스를 생성하고 타이핑 시작
                tempTextBox = new GTextBox(startPoint.x, startPoint.y, "", new Font("Arial", Font.PLAIN, 14));
                currentText = new StringBuilder();
                isTyping = true;
                panel.setTempTextBox(tempTextBox);
                panel.requestFocusInWindow(); // 키 이벤트를 받기 위해 포커스 요청
                
                // 키 리스너 추가
                panel.addKeyListener(textInputAdapter);
            }
        }
    }

    private void finishTextInput() {
        if (isTyping && tempTextBox != null) {
            isTyping = false;
            System.out.println("TextBox add 완료");
            if (currentText.length() > 0) {
                GTextBox newTextBox = new GTextBox(
                    tempTextBox.getX(), 
                    tempTextBox.getY(), 
                    tempTextBox.getText(),
                    tempTextBox.getFont()
                );
                panel.addTextBox(newTextBox);
            }
            tempTextBox = null;
            panel.setTempTextBox(null);
            panel.repaint();
            
            // 임시 키 리스너 제거
            panel.removeKeyListener(textInputAdapter);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (textButton != null && textButton.isSelected() && isMoving && selectedTextBoxIndex != -1) {
            currentPoint = e.getPoint();
            ArrayList<GTextBox> textBoxes = panel.getTextBoxes();
            GTextBox selectedTextBox = textBoxes.get(selectedTextBoxIndex);
            
            int dx = currentPoint.x - startPoint.x;
            int dy = currentPoint.y - startPoint.y;

            // 이동된 위치로 텍스트박스 업데이트
            selectedTextBox.setX(selectedTextBox.getX() + dx);
            selectedTextBox.setY(selectedTextBox.getY() + dy);
            
            startPoint = currentPoint;
            panel.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (textButton != null && textButton.isSelected() && isMoving) {
            selectedTextBoxIndex = -1;
            panel.repaint();
        }
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
