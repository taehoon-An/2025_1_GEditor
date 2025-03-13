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
			JOptionPane.showMessageDialog(null, "계정이 존재하지 않습니다. 계정을 생성해주세요", "로그인 실패!", JOptionPane.ERROR_MESSAGE);
		} else if (account != null && account.getName() == null) {
			JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하셨습니다.", "로그인 실패!", JOptionPane.ERROR_MESSAGE);
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
			if (e.getActionCommand().equals("로그인")) {
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
