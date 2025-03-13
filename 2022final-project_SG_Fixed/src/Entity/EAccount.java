package Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Value.VAccount;

public class EAccount {

	private String id;
	private String password;
	private String name;
	// ...

	public EAccount() {
	}

	public VAccount getLogin(String id, String pw) {
		VAccount vAccount = null;
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			boolean found = false;
			int f = 0;
			while (scanner.hasNext() && !found) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();

				if (this.id.compareTo(id) == 0 && this.password.compareTo(pw) == 0) {
					found = true;
					f = 1;
				} else if (this.id.compareTo(id) == 0 && this.password.compareTo(pw) != 0) {
					found = true;
					f = 2;
					// }
				}
			}
			scanner.close();

			if (f == 1) {
				vAccount = new VAccount();
				vAccount.setId(this.id);
				vAccount.setPassword(this.password);
				vAccount.setName(this.name);

			} else if (f == 2) {
				vAccount = new VAccount();
				vAccount.setId(this.id);
				vAccount.setPassword(this.password);
				vAccount.setName(null);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vAccount;
	}

	public VAccount Account(String id, String pw, String name2) {
		File file = new File("data/account");

		return null;
	}

}
