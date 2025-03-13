
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountFrame() {
		this.setSize(300, 300);

		LayoutManager lm1 = new GridLayout(1, 2); // 왼쪽-오른쪽 두개 배치
		LayoutManager lm2 = new GridLayout(3, 1); // 위-아래

		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fs = this.getSize();

		this.setLocation((scr.width - fs.width) / 2, (scr.height - fs.height) / 2);

		JPanel acP = new JPanel();
		this.add(acP, BorderLayout.CENTER);
		acP.setLayout(lm1);

		JPanel lbP = new JPanel();
		JPanel txP = new JPanel();
		lbP.setLayout(lm2);
		txP.setLayout(lm2);

		acP.add(lbP);
		acP.add(txP);

		JButton btYes = new JButton("완료");
		this.add(btYes, BorderLayout.SOUTH);

		JLabel lbId = new JLabel("아이디");
		JLabel lbPw = new JLabel("패스워드");
		JLabel lbName = new JLabel("사용자 이름");
		lbP.add(lbId);
		lbP.add(lbPw);
		lbP.add(lbName);

		JTextField txId = new JTextField();
		JTextField txPw = new JTextField();
		JTextField txName = new JTextField();
		txId.setColumns(10);
		txPw.setColumns(10);
		txName.setColumns(10);
		txP.add(txId);
		txP.add(txPw);
		txP.add(txName);

	}
}
