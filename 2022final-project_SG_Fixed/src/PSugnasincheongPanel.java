
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Value.VLecture;

public class PSugnasincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel pdP;
	private PControlPanel conP1;
	private PMiridamgiPanel miriP;
	private PControlPanel conP2;
	private PSincheongPanel sinP;
	private JScrollPane scrollPane;

	public PSugnasincheongPanel() {
		ActionHandler actionHandler = new ActionHandler();

		LayoutManager BxLm = new BoxLayout(this, BoxLayout.X_AXIS);// x축으로 layout을 늘여 놓으라.
		this.setLayout(BxLm);

		this.pdP = new PDirectoryPanel();
		this.add(this.pdP);

		this.conP1 = new PControlPanel("1", actionHandler);
		this.add(this.conP1);

		scrollPane = new JScrollPane();
		this.miriP = new PMiridamgiPanel();
		scrollPane.setViewportView(this.miriP);
		this.add(scrollPane);

		this.conP2 = new PControlPanel("2", actionHandler);
		this.add(this.conP2);

		scrollPane = new JScrollPane();
		this.sinP = new PSincheongPanel();
		scrollPane.setViewportView(this.sinP);
		this.add(scrollPane);

	}

	private void moveFromSincheongToMiridamgi() {
		Vector<VLecture> lectures = this.sinP.getSelectedLectures();
		this.miriP.addLectures(lectures);
	}

	private void moveFromMiridamgiToSincheong() {
		Vector<VLecture> lectures = this.miriP.getSelectedLectures();
		this.sinP.addLectures(lectures);
	}

	private void moveFromMiridamgiToLectures() {
		Vector<VLecture> lectures = this.miriP.getSelectedLectures();
		this.pdP.addLectures(lectures);
	}

	private void moveFromLecturesToMiridamgi() {
		Vector<VLecture> lectures = this.pdP.getSelectedLectures();
		this.miriP.addLectures(lectures);
	}

	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().compareTo(">>") == 0) {
				moveFromLecturesToMiridamgi();
				System.out.println("1>>");
			} else if (e.getActionCommand().compareTo("<<") == 0) {
				moveFromMiridamgiToLectures();
				System.out.println("1<<");
			} else if (e.getActionCommand().compareTo(">>") == 0) {
				moveFromMiridamgiToSincheong();
				System.out.println("2>>");
			} else if (e.getActionCommand().compareTo("<<") == 0) {
				moveFromSincheongToMiridamgi();
				System.out.println("2<<");

			}
		}

	}
}