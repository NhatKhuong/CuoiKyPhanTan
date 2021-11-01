package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import BEAN.format;
import DAO.DAO_ThongKe;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class ThongKe_Pn extends JPanel implements ActionListener {
	private DefaultCategoryDataset dataset;
	private JComboBox cbNam;
	private DefaultCategoryDataset dataset2;
	private JComboBox cbNam2;
	private double tongDoanhThuTrongNam = 0;
	private double doanhThuTrungBinhThang = 0;
	private JLabel lbTongDoanhThuNam;
	private JLabel lblDoanhThuTrungBinh;

	/**
	 * Create the panel.
	 */

	public ThongKe_Pn() {
		setSize(1310, 580);
		setLayout(null);
		// danh sach nam rieng biet co du lieu 
		Connection connection = DB.Connect.CreateConnection();
		ArrayList<String > list = DAO_ThongKe.getSoNam(connection);
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1300, 570);
		add(tabbedPane);
		JPanel tap2 = new JPanel();

		cbNam2 = new JComboBox();
		
		cbNam2.setModel(new DefaultComboBoxModel(list.toArray()));
		if(list != null)
			cbNam2.setSelectedIndex(0);
		cbNam2.setBounds(1174, 246, 90, 39);
		cbNam2.addActionListener(this);
		tap2.setLayout(null);

		ChartPanel chartPanel2 = new ChartPanel(createChart2());
		chartPanel2.setBounds(22, 5, 1150, 500);
		chartPanel2.setPreferredSize(new java.awt.Dimension(1200, 500));
		tap2.add(chartPanel2);
		tap2.add(cbNam2);
		tabbedPane.addTab("TOP THUỐC BÁN CHẠY THEO NĂM", null, tap2, null);

		JPanel tap1 = new JPanel();

		cbNam = new JComboBox();
		cbNam.setModel(new DefaultComboBoxModel(list.toArray()));
		if(list != null)
		cbNam.setSelectedIndex(0);
		cbNam.setBounds(1183, 245, 90, 39);
		cbNam.addActionListener(this);
		tap1.setLayout(null);
		//
		// tap1.add(cbNam);
		// tap2.add(cbNam2);

		ChartPanel chartPanel = new ChartPanel(createChart());
		chartPanel.setBounds(22, 5, 1162, 500);
		chartPanel.setPreferredSize(new java.awt.Dimension(1200, 500));
		tap1.add(chartPanel);
		tap1.add(cbNam);
		tabbedPane.addTab("DOANH THU TỪNG THÁNG THEO NĂM", null, tap1, null);
		
		lbTongDoanhThuNam = new JLabel("Tổng doanh thu trong năm:  0 VND");
		lbTongDoanhThuNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTongDoanhThuNam.setBounds(158, 504, 445, 18);
		tap1.add(lbTongDoanhThuNam);
		
		lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình tháng: 0 VND");
		lblDoanhThuTrungBinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDoanhThuTrungBinh.setBounds(679, 504, 451, 18);
		tap1.add(lblDoanhThuTrungBinh);
		cbNam.addActionListener(this);
		cbNam2.addActionListener(this);
		
		lbTongDoanhThuNam.setText("Tổng doanh thu trong năm là: "+format.chuyenDoiTienTe(tongDoanhThuTrongNam));
		int mau = (LocalDate.now().getYear() == Integer.parseInt(cbNam.getSelectedItem().toString())?LocalDate.now().getMonthValue():12);
		lblDoanhThuTrungBinh.setText("Doanh thu trung bình tháng là: "+format.chuyenDoiTienTe(tongDoanhThuTrongNam/mau));
		
	}

	public JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("          BIỂU ĐỒ DOANH THU THEO NĂM", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	public JFreeChart createChart2() {
		JFreeChart barChart = ChartFactory.createBarChart("          BIỂU ĐỒ TOP THUỐC BÁN CHẠY NHẤT NĂM", "Tên thuốc",
				"Số lượng", createDataset2(), PlotOrientation.HORIZONTAL, false, false, false);
		return barChart;
	}

	private CategoryDataset createDataset2() {
		Connection conn = DB.Connect.CreateConnection();
		dataset2 = new DefaultCategoryDataset();
		HashMap<String, Integer> list = DAO_ThongKe.getTop10ThuocBanChayNhatTheoNam(conn,
				Integer.parseInt(cbNam2.getSelectedItem().toString()));
	
		for (Entry<String, Integer> entry : list.entrySet()) {

			dataset2.addValue(entry.getValue(), "Tháng", entry.getKey());
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataset2;
	}

	private CategoryDataset createDataset() {
		Connection conn = DB.Connect.CreateConnection();
		dataset = new DefaultCategoryDataset();
		for (int i = 1; i < 13; i++) {
			tongDoanhThuTrongNam += DAO_ThongKe.getDoanhThuTheoThang(conn, i, Integer.parseInt(cbNam.getSelectedItem().toString()));
			dataset.addValue(
					DAO_ThongKe.getDoanhThuTheoThang(conn, i, Integer.parseInt(cbNam.getSelectedItem().toString())),
					"Tháng" + i, Integer.toString(i));
		}
		
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		Connection conn = DB.Connect.CreateConnection();
		if (object.equals(cbNam)) {
			dataset.clear();
			tongDoanhThuTrongNam = 0;
			for (int i = 1; i < 13; i++) {
				tongDoanhThuTrongNam += DAO_ThongKe.getDoanhThuTheoThang(conn, i, Integer.parseInt(cbNam.getSelectedItem().toString()));
				dataset.addValue(
						DAO_ThongKe.getDoanhThuTheoThang(conn, i, Integer.parseInt(cbNam.getSelectedItem().toString())),
						"Tháng" + i, Integer.toString(i));
			}

			lbTongDoanhThuNam.setText("Tổng doanh thu trong năm là: "+format.chuyenDoiTienTe(tongDoanhThuTrongNam));
			int mau = (LocalDate.now().getYear() == Integer.parseInt(cbNam.getSelectedItem().toString())?LocalDate.now().getMonthValue():12);
			lblDoanhThuTrungBinh.setText("Doanh thu trung bình tháng là: "+format.chuyenDoiTienTe(tongDoanhThuTrongNam/mau));
		} else if (object.equals(cbNam2)) {
			HashMap<String, Integer> list = DAO_ThongKe.getTop10ThuocBanChayNhatTheoNam(conn,
					Integer.parseInt(cbNam2.getSelectedItem().toString()));
			dataset2.clear();
			if (list.size() != 0) {
				for (Entry<String, Integer> entry : list.entrySet()) {
					dataset2.addValue(entry.getValue(), "Tháng", entry.getKey());
				}
			}
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
