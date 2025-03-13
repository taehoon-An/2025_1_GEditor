package Service;
import java.util.Vector;

import Entity.ELecture;
import Value.VLecture;

public class SLecture {

	private ELecture eLecture;

	public SLecture() {
		this.eLecture = new ELecture();

	}

	public Vector<VLecture> getLectures(String fileName) {
		return this.eLecture.getLectures(fileName);
	}

}
