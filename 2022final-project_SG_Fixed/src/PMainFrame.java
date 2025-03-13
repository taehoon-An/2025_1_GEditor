import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Value.VAccount;

public class PMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private VAccount vAccount;
	PAccountPanel accountP;

	public PMainFrame(VAccount vAccount) {
		// attributes
		this.vAccount = vAccount;
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		// vLogin ¼¼ÆÃ
		PSugnasincheongPanel pSugnasincheongPanel = new PSugnasincheongPanel();
		this.add(pSugnasincheongPanel, BorderLayout.CENTER);

		this.accountP = new PAccountPanel(this.vAccount);
		this.add(accountP, BorderLayout.NORTH);

		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fs = this.getSize();

		this.setLocation((scr.width - fs.width) / 2, (scr.height - fs.height) / 2);
	}

	public void initialize() {
		this.setVisible(true);
	}

}
