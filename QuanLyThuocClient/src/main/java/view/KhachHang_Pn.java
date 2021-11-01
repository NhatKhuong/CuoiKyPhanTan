 
package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import BEAN.KhachHang;
import DAO.DAO_KhachHang;
import DB.Connect;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class KhachHang_Pn extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtSDT;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel_1;
	private JComboBox<String> cbGioiTinh;
	private JTextField txtSearch;
	private JButton btnTim;
	private JButton btnLamMoi;
	private JLabel txtPage;
	private JButton btnDau;
	private JButton btnTru1;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JButton btnThemKhachHang;
	private JButton btnSua;

	public KhachHang_Pn() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Nh\u1EADp t\u00EAn: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 10, 91, 41);
		add(lblNewLabel);

		txtSDT = new JTextField();
		txtSDT.setBounds(100, 61, 782, 35);
		add(txtSDT);
		txtSDT.setColumns(10);

		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "(Tất cả)", "Nam", "Nữ" }));
		cbGioiTinh.setBounds(1022, 13, 120, 35);
		cbGioiTinh.addActionListener(this);
		add(cbGioiTinh);

		lblNewLabel_1 = new JLabel("Giới tính");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(892, 13, 120, 35);
		add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 108, 1300, 1);
		add(panel);

		btnThemKhachHang = new JButton("Th\u00EAm");
		btnThemKhachHang.addActionListener(this);
		btnThemKhachHang.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/them.png")));
		btnThemKhachHang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnThemKhachHang.setBounds(1022, 59, 120, 35);
		add(btnThemKhachHang);

		btnSua = new JButton("S\u1EEFa");
		btnSua.addActionListener(this);
		btnSua.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/cap_nhat.png")));
btnSua.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSua.setBounds(1152, 59, 120, 35);
		add(btnSua);

		btnDau = new JButton("");
		btnDau.addActionListener(this);
		btnDau.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDau.setBounds(422, 530, 80, 30);
		add(btnDau);

		btnTru1 = new JButton("");
		btnTru1.addActionListener(this);
		btnTru1.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTru1.setBounds(517, 530, 80, 30);
		add(btnTru1);

		btnCong1 = new JButton("");
		btnCong1.addActionListener(this);
		btnCong1.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/forward-button.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCong1.setBounds(696, 530, 80, 30);
		add(btnCong1);

		JLabel lblNhpSdt = new JLabel("Nhập SDT: ");
		lblNhpSdt.setForeground(Color.BLACK);
		lblNhpSdt.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhpSdt.setBounds(10, 58, 91, 41);
		add(lblNhpSdt);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);

		txtSearch.setBounds(100, 15, 782, 35);
		txtSearch.addKeyListener(this);
		add(txtSearch);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(this);
		btnLamMoi.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/lam_moi.png")));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLamMoi.setBounds(1152, 13, 120, 35);
		add(btnLamMoi);

		btnCuoi = new JButton("");
		btnCuoi.addActionListener(this);
		btnCuoi.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/nextEnd.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCuoi.setBounds(791, 530, 80, 30);
		add(btnCuoi);

		txtPage = new JLabel("1");
		txtPage.setForeground(new Color(0, 0, 0));
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPage.setBounds(644, 530, 46, 30);
		add(txtPage);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 119, 1290, 401);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Mã khách hàng",
				"Tên Khách hàng", "Giới tính", "SDT", "Tỉnh/TP", "Quận/Huyện", "Phường xã" }));
		table.setRowHeight(35);

		btnTim = new JButton("Tim");
		btnTim.setIcon(new ImageIcon(KhachHang_Pn.class.getResource("/img/Search.png")));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.addActionListener(this);
		btnTim.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTim.setBounds(892, 59, 120, 35);
		add(btnTim);
		themDuLieuVaoTable();

		// khong cho sua table
		table.setDefaultEditor(Object.class, null);
		
	}
	public void khoiTaoDuLieu(){
		xoaALLDuLieuTable();
		themDuLieuVaoTable();
	}
	private void xoaALLDuLieuTable() {
		for(int i = tableModel.getRowCount() ; i>0; i--) {
		tableModel.removeRow(0);
		}
		
	}

	public void themDuLieuVaoTable() {

		Connection conn = DB.Connect.CreateConnection();
int gtIndex = cbGioiTinh.getSelectedIndex();
		// loc theo gioi tinh
		String gt = "";
		if (gtIndex == 1) {
			gt = "1";
		} else if (gtIndex == 2) {
			gt = "0";
		}
		// phan trang
		int page = Integer.parseInt(txtPage.getText());
		// loc theo search
		String search = txtSearch.getText();
		if (search.trim().length() == 0) {
			search = "";
		}
		ArrayList<KhachHang> list = DAO.DAO_KhachHang.danhSachKhachHang(conn, page - 1, search, gt);
		if (list != null) {
			for (KhachHang khachHang : list) {
				String[] s = { khachHang.getMaKhachHang(), khachHang.getTenKhachHang().toUpperCase(),
						khachHang.isGioiTinh() ? "Nam" : "Nữ", khachHang.getSoDienThoai(),
						khachHang.getDiaChi().getTinhTp(), khachHang.getDiaChi().getQuanHuyen(),
						khachHang.getDiaChi().getPhuongXa() };
				tableModel.addRow(s);
			}
		} else {
			xoaALLDuLieuTable();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROE close :themDuLieuVaoTable" + e.getMessage());
		}
	}

	public void lamMoi() {
		cbGioiTinh.setSelectedIndex(0);
		txtPage.setText("1");
		txtSDT.setText("");
		txtSearch.setText("");
		xoaALLDuLieuTable();
		themDuLieuVaoTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection conn = DB.Connect.CreateConnection();
		String gioiTinh = "";
		if (cbGioiTinh.getSelectedIndex( ) != 0) {
			gioiTinh  = cbGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? "1" : "0";
		}
		int tongSoHang = DAO_KhachHang.tongSoHang(conn,txtSearch.getText(), gioiTinh );
		int pageCuoi = tongSoHang % 20 == 0 ? tongSoHang / 20 : tongSoHang / 20 + 1;
		Object object = e.getSource();
		if (object.equals(cbGioiTinh)) { // loc du lieu theo gioi tinh
			xoaALLDuLieuTable();
			themDuLieuVaoTable();
		} else if (object.equals(btnTim)) { // tim kiem qua sdt
			xoaALLDuLieuTable();
			String sdt = txtSDT.getText();
			if (sdt.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "Ban chua nhap SDT");
				return;
			}
			KhachHang khachHang = DAO_KhachHang.layThongTinKhachHangQuaSDT(conn, sdt);
			if (khachHang != null) {
				tableModel.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getTenKhachHang(),
						khachHang.isGioiTinh() ? "Nam" : "Nữ", khachHang.getSoDienThoai(),
						khachHang.getDiaChi().getTinhTp(), khachHang.getDiaChi().getQuanHuyen(),
						khachHang.getDiaChi().getPhuongXa() });
			} else {
				JOptionPane.showMessageDialog(this, "Khong tim thay");
			}
		} else if (object.equals(btnLamMoi)) {
			lamMoi();
		}
		// phan trang
		else if (object.equals(btnDau)) {
			txtPage.setText("1");
			xoaALLDuLieuTable();
			themDuLieuVaoTable();

		} else if (object.equals(btnCong1)) {
			int page = Integer.parseInt(txtPage.getText());
			if (page != pageCuoi) {
				page++;
				txtPage.setText(Integer.toString(page));
				xoaALLDuLieuTable();
				themDuLieuVaoTable();
			}
		} else if (object.equals(btnTru1)) {
			int page = Integer.parseInt(txtPage.getText());
			if (page != 1) {
				page--;
txtPage.setText(Integer.toString(page));
				xoaALLDuLieuTable();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnCuoi)) {
			txtPage.setText(Integer.toString(pageCuoi));
			xoaALLDuLieuTable();
			themDuLieuVaoTable();
		} else if (object.equals(btnThemKhachHang)) { // them khach hang
			DialogThemKhachHang dialogThemKhachHang = new DialogThemKhachHang(null);
			dialogThemKhachHang.setVisible(true);
			lamMoi();
		}else if(object.equals(btnSua)) {
			int selectRow = table.getSelectedRow();
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "Ban chua chon khach hang");
				return;
			}
			String sdt = (String) table.getValueAt(table.getSelectedRow(), 3);
		
			DialogSuaKhachHang dialogSuaKhachHang = new DialogSuaKhachHang(sdt);
			dialogSuaKhachHang.setVisible(true);
			lamMoi();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		txtPage.setText("1");
		xoaALLDuLieuTable();
		themDuLieuVaoTable();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
