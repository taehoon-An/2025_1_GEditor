

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
		lInsa.setText("�� �ȳ��ϼ���.");
		this.add(lInsa);

		JLabel lLogin = new JLabel();
		lLogin.setText(" �α��� �ð� : ");
		this.add(lLogin);

		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		JLabel lTime = new JLabel(sd.format(new Date()));
		this.add(lTime);

		JLabel lText = new JLabel();
		lText.setText("�Դϴ�");
		this.add(lText);

	}

}
