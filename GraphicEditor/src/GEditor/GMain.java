package GEditor;

public class GMain {

	public static void main(String[] args) {
		GMainFrame mainFrame = new GMainFrame(); //메인프레임을 하나 만듬.
		mainFrame.setVisible(true); //mainFrame을 그리라고 명령하는 것. 
		
		mainFrame.initialize();
	}

}
