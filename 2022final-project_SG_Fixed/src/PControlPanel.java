import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton btRight;
	private JButton btLeft;

	public PControlPanel(String panelId, PSugnasincheongPanel.ActionHandler actionHandler) {
		LayoutManager lm = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(lm);

		this.btRight = new JButton(">>");
		this.btRight.addActionListener(actionHandler);
		this.btRight.setActionCommand(this.btRight.getText());
		this.add(this.btRight);

		this.btLeft = new JButton("<<");
		this.btLeft.addActionListener(actionHandler);
		this.btLeft.setActionCommand(this.btLeft.getText());
		this.add(this.btLeft);
	}

}
