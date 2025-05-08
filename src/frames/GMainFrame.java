package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import handlers.RightClickListener;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	//Color color;            ]
	//Location location;      ] -> 이 두가지는 원래 속성으로 추가해야하지만, 상속받아서 사용이 가능하기 때문에 JFrame에 있다는 것. 
	//attributes
	private GMenuBar menuBar;
	private GShapeToolBar toolBar;// 부품 정의
	private GDrawingPanel drawingPanel; //여기서 부품화를 시킬거라는 것. 왜냐하면 JFrame이 가장 큰 Window이기 떄문에 여기서
	// 즉, 특화해서 Class안으로 집어넣게 만들어야 함. 
	private GShapeMenu shapeMenu;
	
	public GMainFrame() {
		//attributes
		this.setLocation(100, 200);
		this.setSize(1080, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//components	
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		this.shapeMenu = new GShapeMenu();//마우스 우클릭 시 나오게 하는 Menu를 출력하기 위해 JPopupMenu사용
		
		this.setLayout(new BorderLayout());	
		
		this.toolBar = new GShapeToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		this.drawingPanel.addMouseListener(new RightClickListener(this.shapeMenu)); //마우스 우클릭 시 Shape조정 메뉴가 나오는 이벤트핸들러
		
		//associated Attributes
		//이 속성은 자식들과도 연결되어있는 속성이기 때문에 절차적으로 자식들이 다 생성되고 그 다음에 작동하도록 아래에 등록
		this.setVisible(true); //mainFrame을 그리라고 명령하는 것. 
	}
	
	public void initialize() {
		//associate
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
		
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
	
	

}
