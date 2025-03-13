package Service;
import Entity.EAccount;
import Value.VAccount;

public class SLogin {

	private EAccount eAccount;

	public SLogin() {
		this.eAccount = new EAccount();
	}

	public VAccount login(String id, String pw) {
		VAccount vAccount = this.eAccount.getLogin(id, pw);

		return vAccount;
	}

	public VAccount Account(String id, String pw, String name) {
		VAccount vAccount = this.eAccount.Account(id, pw, name);

		return vAccount;
	}

}
