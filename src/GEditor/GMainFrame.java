package GEditor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import GHandler.GOvalHandler;
import GHandler.GPolHandler;
import GHandler.GRecHandler;
import GHandler.GTextHandler;
import GHandler.GTriHandler;
import GHandler.RightClickListener;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	//Color color;            ]
	//Location location;      ] -> 이 두가지는 원래 속성으로 추가해야하지만, 상속받아서 사용이 가능하기 때문에 JFrame에 있다는 것. 
	//attributes
	private GMenuBar menuBar;
	private GToolBar toolBar;// 부품 정의
	private GDrawingPanel drawingPanel; //여기서 부품화를 시킬거라는 것. 왜냐하면 JFrame이 가장 큰 Window이기 떄문에 여기서
	// 즉, 특화해서 Class안으로 집어넣게 만들어야 함. 
	private GShape shapeMenu;
	private GRecHandler recHandler;
	private GTriHandler triHandler;
	private GPolHandler polHandler;
	private GOvalHandler ovalHandler;
	private GTextHandler textHandler;
	
	public GMainFrame() {
		//attributes
		this.setSize(1080, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());	
		
		//components	
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		this.shapeMenu = new GShape();//마우스 우클릭 시 나오게 하는 Menu를 출력하기 위해 JPopupMenu사용
		
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		this.drawingPanel.addMouseListener(new RightClickListener(this.shapeMenu)); //마우스 우클릭 시 Shape조정 메뉴가 나오는 이벤트핸들러
		
		//associated Attributes
		//이 속성은 자식들과도 연결되어있는 속성이기 때문에 절차적으로 자식들이 다 생성되고 그 다음에 작동하도록 아래에 등록
		this.setVisible(true); //mainFrame을 그리라고 명령하는 것. 
	}
	
	public void initialize() {
		 //마우스 우클릭 시 Shape조정 메뉴가 나오는 이벤트핸들러
		this.drawingPanel.addMouseListener(new RightClickListener(this.shapeMenu));
		
		this.recHandler = new GRecHandler(drawingPanel);
		this.triHandler = new GTriHandler(drawingPanel);
		this.polHandler = new GPolHandler(drawingPanel);
		this.ovalHandler = new GOvalHandler(drawingPanel);
		this.textHandler = new GTextHandler(drawingPanel);
		
		this.drawingPanel.setRecHandler(recHandler);
		this.drawingPanel.setRecButton(toolBar.getRecButton());
		this.drawingPanel.setTriHandler(triHandler);
		this.drawingPanel.setTriButton(toolBar.getTriButton());
		this.drawingPanel.setPolHandler(polHandler);
		this.drawingPanel.setPolButton(toolBar.getPolButton());
		this.drawingPanel.setOvalHandler(ovalHandler);
		this.drawingPanel.setOvalButton(toolBar.getOvalButton());
		this.drawingPanel.setTextHandler(textHandler);
        this.drawingPanel.setTextButton(toolBar.getTextButton());
		
		this.shapeMenu.initialize(recHandler);
		this.shapeMenu.initialize(triHandler);
		this.shapeMenu.initialize(polHandler);
		this.shapeMenu.initialize(ovalHandler);
		this.shapeMenu.initialize(textHandler);
		
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
	
	

}
