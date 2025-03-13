
import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Service.SDirectory;
import Value.VDirectory;
import Value.VLecture;

public class PDirectoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListSelectionHandler listSelectionHandler;
	private PDirectory campusTable;
	private PDirectory collegeTable;
	private PDirectory departmentTable;
	private PLectureTable lectureTable;
	public VDirectory vDirectory;

	String fileName = null;

	public PDirectoryPanel() {

		JPanel subP1 = new JPanel();
		JPanel subP2 = new JPanel();

		this.listSelectionHandler = new ListSelectionHandler();

		JScrollPane scrollPane = new JScrollPane();
		this.campusTable = new PDirectory();
		this.campusTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		scrollPane.setViewportView(this.campusTable);
		subP1.add(scrollPane);

		scrollPane = new JScrollPane();
		this.collegeTable = new PDirectory();
		this.collegeTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		scrollPane.setViewportView(this.collegeTable);
		subP1.add(scrollPane);

		scrollPane = new JScrollPane();
		this.departmentTable = new PDirectory();
		this.departmentTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		scrollPane.setViewportView(this.departmentTable);
		subP1.add(scrollPane);

		scrollPane = new JScrollPane();
		this.lectureTable = new PLectureTable();
		this.lectureTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		scrollPane.setViewportView(this.lectureTable);
		subP2.add(scrollPane);

		LayoutManager BoxLm = new BoxLayout(subP1, BoxLayout.X_AXIS);
		subP1.setLayout(BoxLm);

		LayoutManager BxLm2 = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(BxLm2);

		this.add(subP1);
		this.add(subP2);

		this.updateTable(null);
	}

	public Vector<VLecture> getSelectedLectures() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLectures(Vector<VLecture> lectures) {
		// TODO Auto-generated method stub

	}

	private void updateTable(Object object) {
		if (object == null) {
			fileName = "root";
			fileName = this.campusTable.setData(fileName);
			fileName = this.collegeTable.setData(fileName);
			fileName = this.departmentTable.setData(fileName);
			this.lectureTable.setData(fileName);

		} else if (object == (this.campusTable.getSelectionModel())) {
			if (this.campusTable.getRowCount() == this.campusTable.getVDirectories().size()) {
				fileName = this.campusTable.getVDirectories().get(this.campusTable.getSelectedRow()).getFileName();
				System.out.println(fileName);
				fileName = this.collegeTable.setData(fileName);
				fileName = this.departmentTable.setData(fileName);
				this.lectureTable.setData(fileName);
			}

		} else if (object == (this.collegeTable.getSelectionModel())) {
			if (this.collegeTable.getRowCount() == this.collegeTable.getVDirectories().size()) {
				fileName = this.collegeTable.getVDirectories().get(this.collegeTable.getSelectedRow()).getFileName();
				System.out.println(fileName);
				fileName = this.departmentTable.setData(fileName);
				this.lectureTable.setData(fileName);
			}

		} else if (object == (this.departmentTable.getSelectionModel())) {
			if (this.departmentTable.getRowCount() == this.departmentTable.getVDirectories().size()) {
				fileName = this.departmentTable.getVDirectories().get(this.departmentTable.getSelectedRow())
						.getFileName();
				this.lectureTable.setData(fileName);
			}

		}

	}

	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				fileName = null;
				System.out.println(e.getSource().toString());
				updateTable(e.getSource());

			}

		}

	}

	private class PDirectory extends JTable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DefaultTableModel tableModel;
		private Vector<VDirectory> vDirectories;

		public PDirectory() {
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			Vector<String> header = new Vector<String>();
			header.add("");
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);

		}

		private Vector<VDirectory> getVDirectories() {
			return this.vDirectories;
		}

		public String setData(String fileName) {
			SDirectory sDirectory = new SDirectory();
			vDirectories = sDirectory.getDirectories(fileName);

			this.tableModel.setNumRows(0);

			for (VDirectory vDirectory : vDirectories) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);
			}

			// this.setRowSelectionInterval(0,0);
			return vDirectories.get(0).getFileName();
		}

	}

}
