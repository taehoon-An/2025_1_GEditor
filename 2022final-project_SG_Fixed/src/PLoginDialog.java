import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Service.SLogin;
import Value.VAccount;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;
	JButton btLogin;
	JButton btAccount;
	VAccount vAccount;

	private SLogin sLogin;

	public PLoginDialog(ActionListener actionHandler) {
		this.setSize(300, 150);
		super.setModal(true);

		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		LayoutManager lm1 = new GridLayout(1, 2); // 왼쪽-오른쪽 두개 배치
		LayoutManager lm2 = new GridLayout(2, 1); // 위-아래로 두개 배치

		JPanel mainPanel = new JPanel();// 버튼 배치, 메인배치로 버튼과 페널 두개설지할 메인패널 설정
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(lm1);

		JPanel Panel1 = new JPanel();
		JPanel Panel2 = new JPanel();
		JPanel Panel3 = new JPanel();

		mainPanel.add(Panel1);
		mainPanel.add(Panel2);
		this.add(Panel3, BorderLayout.SOUTH);

		Panel1.setLayout(lm2);
		Panel2.setLayout(lm2);

		JLabel lbId = new JLabel("아이디");
		Panel1.add(lbId);

		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		Panel2.add(this.tfId);

		JLabel lbPassword = new JLabel("비밀번호");
		Panel1.add(lbPassword);

		this.tfPassword = new JPasswordField();
		this.tfPassword.setColumns(10);
		Panel2.add(this.tfPassword);

		JButton btLogin = new JButton("로그인");
		Panel3.add(btLogin);
		btLogin.setSize(80, 50);

		JButton btAccount = new JButton("회원가입");
		Panel3.add(btAccount);

		btLogin.addActionListener(actionHandler);
		btAccount.addActionListener(actionHandler);

		this.sLogin = new SLogin();

		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fs = this.getSize();

		this.setLocation((scr.width - fs.width) / 2, (scr.height - fs.height) / 2);

	}

	public VAccount login() {
		String Sid = this.tfId.getText();
		String Spassword = this.tfPassword.getText();

		VAccount vAccount = sLogin.login(Sid, Spassword);// VLogin데이터를 가져왔음

		return vAccount;

	}

}
