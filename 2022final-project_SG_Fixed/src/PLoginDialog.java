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

		LayoutManager lm1 = new GridLayout(1, 2); // ����-������ �ΰ� ��ġ
		LayoutManager lm2 = new GridLayout(2, 1); // ��-�Ʒ��� �ΰ� ��ġ

		JPanel mainPanel = new JPanel();// ��ư ��ġ, ���ι�ġ�� ��ư�� ��� �ΰ������� �����г� ����
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

		JLabel lbId = new JLabel("���̵�");
		Panel1.add(lbId);

		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		Panel2.add(this.tfId);

		JLabel lbPassword = new JLabel("��й�ȣ");
		Panel1.add(lbPassword);

		this.tfPassword = new JPasswordField();
		this.tfPassword.setColumns(10);
		Panel2.add(this.tfPassword);

		JButton btLogin = new JButton("�α���");
		Panel3.add(btLogin);
		btLogin.setSize(80, 50);

		JButton btAccount = new JButton("ȸ������");
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

		VAccount vAccount = sLogin.login(Sid, Spassword);// VLogin�����͸� ��������

		return vAccount;

	}

}
