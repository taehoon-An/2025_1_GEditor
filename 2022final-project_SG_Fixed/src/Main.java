import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Value.VAccount;

public class Main {
	private PLoginDialog loginDialog;
	private ActionHandler actionHandler;

	public Main() {

	}

	private void initialize() {
		this.actionHandler = new ActionHandler();

		this.loginDialog = new PLoginDialog(actionHandler);
		this.loginDialog.setVisible(true);
	}

	private void run() {
		VAccount account = this.loginDialog.login();
		this.loginDialog.dispose();
		if (account == null) {
			JOptionPane.showMessageDialog(null, "������ �������� �ʽ��ϴ�. ������ �������ּ���", "�α��� ����!", JOptionPane.ERROR_MESSAGE);
		} else if (account != null && account.getName() == null) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.", "�α��� ����!", JOptionPane.ERROR_MESSAGE);
		} else {
			this.loginDialog.dispose();
			PMainFrame pmainFrame = new PMainFrame(account);
			pmainFrame.initialize();
		}
	}

	private void finish() {

	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("�α���")) {
				run();
			}
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		// main.run();
		// main.finish();

	}
}
