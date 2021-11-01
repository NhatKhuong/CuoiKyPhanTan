package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BEAN.NhanVien;
import DAO.DAO_NhanVien;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class NhanVien_Pn extends JPanel implements ActionListener, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtSDT;
	private JTextField txtTimTen;
	private JLabel txtPage;
	private JTable table;
	private DefaultTableModel tableModle;
	private JButton btnDau;
	private JButton btnTru1;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JButton btnLamMoi;
	private JButton btnTim;
	private JComboBox<String> cbTrangThaiLamViec;
	private JButton btnTrangThaiLamViec;
	private JButton btnThem;
	private JButton btnSua;

	public NhanVien_Pn() {
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Nh\u1EADp t\u00EAn: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 10, 91, 41);
		add(lblNewLabel);

		txtSDT = new JTextField();
		txtSDT.setBounds(100, 60, 650, 35);
		add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tr\u1EA1ng th\u00E1i l\u00E0m vi\u1EC7c");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(762, 10, 186, 35);
		add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 108, 1300, 1);
		add(panel);

		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(this);
		btnThem.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/them.png")));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 16));
		btnThem.setBounds(892, 60, 120, 35);
		add(btnThem);

		btnTrangThaiLamViec = new JButton("TTLV");
		btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/lam_moi.png")));
		btnTrangThaiLamViec.addActionListener(this);
		btnTrangThaiLamViec.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTrangThaiLamViec.setBounds(1022, 58, 120, 35);
		add(btnTrangThaiLamViec);

		btnSua = new JButton("S\u1EEFa");
		btnSua.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/cap_nhat.png")));
		btnSua.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSua.addActionListener(this);
		btnSua.setBounds(1152, 58, 120, 35);
		add(btnSua);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 119, 1290, 401);
		add(scrollPane);

		tableModle = new DefaultTableModel(new Object[][] {

		}, new String[] { "T\u00EAn nh\u00E2n vi\u00EAn", "Gi\u1EDBi t\u00EDnh", "SDT", "T\u1EC9nh/TP",
				"Qu\u1EADn/Huy\u1EC7n", "Ph\u01B0\u1EDDng x\u00E3", "Tr\u1EA1ng th\u00E1i" });
		table = new JTable();
		table.addMouseListener(this);
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		table.setModel(tableModle);

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
		lblNhpSdt.setBounds(10, 60, 91, 41);
		add(lblNhpSdt);

		txtTimTen = new JTextField();
		txtTimTen.setColumns(10);
		txtTimTen.setBounds(100, 10, 650, 35);
		txtTimTen.addKeyListener(this);
		add(txtTimTen);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(this);
		btnLamMoi.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/lam_moi.png")));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLamMoi.setBounds(1152, 10, 120, 35);
		add(btnLamMoi);

		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/nextEnd.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCuoi.setBounds(791, 530, 85, 30);
		btnCuoi.addActionListener(this);
		add(btnCuoi);

		txtPage = new JLabel("1");
		txtPage.setForeground(new Color(0, 0, 0));
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPage.setBounds(644, 530, 46, 30);
		add(txtPage);

		cbTrangThaiLamViec = new JComboBox<String>();
		cbTrangThaiLamViec.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTrangThaiLamViec
				.setModel(new DefaultComboBoxModel<String>(new String[] { "(Tất cả)", "Đã nghĩ", "Đang làm" }));
		cbTrangThaiLamViec.setBounds(921, 10, 220, 35);
		cbTrangThaiLamViec.addActionListener(this);
		add(cbTrangThaiLamViec);

		btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/Search.png")));
		btnTim.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTim.setBounds(762, 60, 120, 35);
		btnTim.addActionListener(this);
		add(btnTim);

		// khong cho sua table
		table.setDefaultEditor(Object.class, null);
		// add du lieu
		themDuLieuVaoTable();
	}

	public void themDuLieuVaoTable() {

		int page = Integer.parseInt(txtPage.getText());
		Connection conn = DB.Connect.CreateConnection();
		String trangThaiLamViec = "";
		if (cbTrangThaiLamViec.getSelectedIndex() != 0) {
			trangThaiLamViec = cbTrangThaiLamViec.getSelectedItem().toString().equalsIgnoreCase("Đang làm") ? "1" : "0";
		}
		ArrayList<NhanVien> list = null;
		// loc theo trang thai lam viec
		list = DAO_NhanVien.DanhSachNhanVien(conn, page - 1, txtTimTen.getText().trim(), trangThaiLamViec);

		if (list == null) {
			JOptionPane.showMessageDialog(this, "rong");
		}
		for (NhanVien nhanVien : list) {
			String[] s = { nhanVien.getTenNhanVien().toUpperCase(), nhanVien.isGioiTinh() ? "Nam" : "Nũ", nhanVien.getSoDienThoaiNV(),
					nhanVien.getDiaChi().getTinhTp(), nhanVien.getDiaChi().getQuanHuyen(),
					nhanVien.getDiaChi().getPhuongXa(), nhanVien.isTrangThaiLamViec() ? "Đang làm" : "Đã nghĩ" };
			tableModle.addRow(s);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("DOng bi loi (ThemDuLieuVaoTable)");
		}
	}

	public void xoaToanBoBang() {
		for (int i = tableModle.getRowCount(); i > 0; i--) {
			tableModle.removeRow(0);
		}

	}

	public void khoiTaoDuLieu() {
		xoaToanBoBang();
		themDuLieuVaoTable();
	}

	public void lamMoi() {
		btnTrangThaiLamViec = new JButton("TTLV");
		cbTrangThaiLamViec.setSelectedIndex(0);
		txtTimTen.setText("");
		txtPage.setText("1");
		txtSDT.setText("");
		xoaToanBoBang();
		themDuLieuVaoTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		Connection conn = DB.Connect.CreateConnection();
		int TongPage = 1;
		String trangThaiLamViec = "";
		if (cbTrangThaiLamViec.getSelectedIndex() != 0) {
			trangThaiLamViec = cbTrangThaiLamViec.getSelectedItem().toString().equalsIgnoreCase("Đang làm") ? "1" : "0";
		}
		int tongHang = DAO_NhanVien.tongHang(conn, txtTimTen.getText(), trangThaiLamViec);
		TongPage = tongHang % 20 == 0 ? tongHang / 20 : tongHang / 20 + 1;
		if (object.equals(btnCong1)) { // next page table

			// next table
			int page = Integer.parseInt(txtPage.getText()) + 1;

			if (page <= TongPage) {
				txtPage.setText(Integer.toString(page));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}
		} else if (object.equals(btnTru1)) { // Lui page table

			int page = Integer.parseInt(txtPage.getText()) - 1;

			if (page >= 1) {
				txtPage.setText(Integer.toString(page));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnCuoi)) { // Cuoi page table
			int page = Integer.parseInt(txtPage.getText());

			if (page != TongPage) {
				txtPage.setText(Integer.toString(TongPage));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnDau)) { // Dau page table

			int page = Integer.parseInt(txtPage.getText());

			if (page != 0) {
				txtPage.setText("1");
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnLamMoi)) { // lam moi
			lamMoi();
		} else if (object.equals(btnTim)) { // tim SDT
			String sdt = txtSDT.getText();
			if (!sdt.trim().equals("")) {
				NhanVien nhanVien = DAO_NhanVien.layThongTinNhanVienQuaSDT(conn, sdt);
				try {
					conn.close();
				} catch (SQLException e1) {
					System.err.println("Dong loi (Bnt cong)");
				}
				if (nhanVien != null) {
					xoaToanBoBang();
					txtPage.setText("1");
					txtTimTen.setText("");
					String[] s = { nhanVien.getTenNhanVien(), nhanVien.isGioiTinh() ? "Nam" : "Nũ",
							nhanVien.getSoDienThoaiNV(), nhanVien.getDiaChi().getTinhTp(),
							nhanVien.getDiaChi().getQuanHuyen(), nhanVien.getDiaChi().getPhuongXa(),
							nhanVien.isGioiTinh() ? "Đang làm" : "Đã nghĩ" };
					tableModle.addRow(s);
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}
		} else if (object.equals(cbTrangThaiLamViec)) { // loc du lieu theo trang thai lam viec
			txtPage.setText("1");
			xoaToanBoBang();
			themDuLieuVaoTable();
		} else if (object.equals(btnTrangThaiLamViec)) { // thay doi trang thai lam viec
			int hang = table.getSelectedRow();
			if (hang == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn Nhân viên");
				return;
			}
			int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi trạng thái làm việc không",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (xacnhan == JOptionPane.NO_OPTION) {
				return;
			}
			DAO_NhanVien.suaTrangThaiLamViec(conn,
					DAO_NhanVien.layMaNhanVienQuaSoDienThoai(conn, tableModle.getValueAt(hang, 2).toString()),
					tableModle.getValueAt(hang, 6).toString().equalsIgnoreCase("Đang Làm") ? false : true);
			tableModle.setValueAt(
					tableModle.getValueAt(hang, 6).toString().equalsIgnoreCase("Đang Làm") ? "Đã nghĩ" : "Đang làm",
					hang, 6);
			String trangThaiLamViec2 = tableModle.getValueAt(table.getSelectedRow(), 6).toString();
			if (trangThaiLamViec2.equalsIgnoreCase("Đã nghĩ")) {
				btnTrangThaiLamViec.setText("Làm lại");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/lam_moi.png")));
			} else {
				btnTrangThaiLamViec.setText("Nghĩ việc");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/xoa.png")));
			}
			JOptionPane.showMessageDialog(this, "Thay đổi thành công");
		} else if (object.equals(btnThem)) {// them nhan vien
			new DialogThemNhanVien().setVisible(true);

			lamMoi();
		} else if (object.equals(btnSua)) {
			int selectRow = table.getSelectedRow();
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui Lòng click chọn Nhân viên");
				return;
			}
			new DialogSuaNhanVien(tableModle.getValueAt(selectRow, 2).toString()).setVisible(true);
			lamMoi();
		}
		// dong ket noi
		try

		{
			conn.close();
		} catch (SQLException e1) {
			System.err.println("Dong loi (Bnt cong)");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// set trang thai lam viec
		Object object = e.getSource();
		if (object.equals(table)) {
			String trangThaiLamViec = tableModle.getValueAt(table.getSelectedRow(), 6).toString();
			if (trangThaiLamViec.equalsIgnoreCase("Đã nghĩ")) {
				btnTrangThaiLamViec.setText("Làm lại");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/lam_moi.png")));
			} else {
				btnTrangThaiLamViec.setText("Nghĩ việc");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVien_Pn.class.getResource("/img/xoa.png")));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		txtPage.setText("1");
		xoaToanBoBang();
		themDuLieuVaoTable();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}