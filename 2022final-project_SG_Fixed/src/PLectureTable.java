import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Service.SLecture;
import Value.VLecture;

public class PLectureTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultTableModel tableModel;
	private Vector<VLecture> vLectures;

	public PLectureTable() {

		Vector<String> header = new Vector<String>();
		header.add("강좌번호");
		header.add("강좌이름");
		header.add("교수");
		header.add("학점");
		header.add("시간");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);

	}

	public void setData(String fileName) {
		SLecture sLecture = new SLecture();
		Vector<VLecture> vLectures = sLecture.getLectures(fileName);
		this.tableModel.setNumRows(0);

		for (VLecture vLecture : vLectures) {
			Vector<String> row = new Vector<String>();
			row.add(vLecture.getId());
			row.add(vLecture.getName());
			row.add(vLecture.getProfessor());
			row.add(vLecture.getCredit());
			row.add(vLecture.getTime());
			this.tableModel.addRow(row);
		}
		// this.setRowSelectionInterval(0, 0);
	}
}
