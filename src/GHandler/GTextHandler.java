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
    private StringBuilder currentText = new StringBuilder();//�� String����ȭ�� ���� classȰ��
    private KeyAdapter textInputAdapter;

    public GTextHandler(GDrawingPanel panel) {
        this.panel = panel;
        
        // Ű �Է��� ���� ����� ����
        textInputAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (isTyping && tempTextBox != null) {
                    char c = e.getKeyChar();
                    if (c == KeyEvent.VK_ENTER) {
                        // Enter Ű�� ������ �ؽ�Ʈ �Է� �Ϸ�
                        finishTextInput();
                    } else if (c == KeyEvent.VK_BACK_SPACE) {
                        // �齺���̽� ó��
                        if (currentText.length() > 0) {
                            currentText.deleteCharAt(currentText.length() - 1);
                            tempTextBox.setText(currentText.toString());
                            panel.repaint();
                        }
                    } else {
                        // ���ڸ� �ؽ�Ʈ�� �߰�
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
                // �׸��� ��� - �� �ؽ�Ʈ �ڽ��� �����ϰ� Ÿ���� ����
                tempTextBox = new GTextBox(startPoint.x, startPoint.y, "", new Font("Arial", Font.PLAIN, 14));
                currentText = new StringBuilder();
                isTyping = true;
                panel.setTempTextBox(tempTextBox);
                panel.requestFocusInWindow(); // Ű �̺�Ʈ�� �ޱ� ���� ��Ŀ�� ��û
                
                // Ű ������ �߰�
                panel.addKeyListener(textInputAdapter);
            }
        }
    }

    private void finishTextInput() {
        if (isTyping && tempTextBox != null) {
            isTyping = false;
            System.out.println("TextBox add �Ϸ�");
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
            
            // �ӽ� Ű ������ ����
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

            // �̵��� ��ġ�� �ؽ�Ʈ�ڽ� ������Ʈ
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
