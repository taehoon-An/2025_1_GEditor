

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Value.VAccount;

public class PAccountPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PAccountPanel(VAccount vAccount) {
		JLabel lName = new JLabel(vAccount.getName());
		this.add(lName);

		JLabel lInsa = new JLabel();
		lInsa.setText("님 안녕하세요.");
		this.add(lInsa);

		JLabel lLogin = new JLabel();
		lLogin.setText(" 로그인 시간 : ");
		this.add(lLogin);

		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		JLabel lTime = new JLabel(sd.format(new Date()));
		this.add(lTime);

		JLabel lText = new JLabel();
		lText.setText("입니다");
		this.add(lText);

	}

}
