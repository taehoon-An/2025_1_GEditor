package GEditor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GToolBar toolBar;// 부품 정의
	private GDrawingPanel drawingPanel; //여기서 부품화를 시킬거라는 것. 왜냐하면 JFrame이 가장 큰 Window이기 떄문에 여기서
	// 즉, 특화해서 Class안으로 집어넣게 만들어야 함. 
	private GShape shapeMenu;
	
	public GMainFrame() {
		//attributes
		this.setLocation(100, 200);
		this.setSize(1080, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//components
		this.setLayout(new BorderLayout());		
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		this.shapeMenu = new GShape();//마우스 우클릭 시 나오게 하는 Menu를 출력하기 위해 JPopupMenu사용
		
		this.drawingPanel.addMouseListener(new RightClickListener(this.shapeMenu)); //마우스 우클릭 시 Shape조정 메뉴가 나오는 이벤트핸들러
		
		
	}
	
	public void initialize() {
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
	
	

}
