package Value;
import java.util.Scanner;

public class VDirectory {
	private String id;
	private String name;
	private String fileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void read(Scanner scanner) {
		this.id = scanner.next();
		this.name = scanner.next();
		this.fileName = scanner.next();

	}

}
