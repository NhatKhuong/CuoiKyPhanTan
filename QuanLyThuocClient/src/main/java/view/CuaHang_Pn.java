package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import BEAN.CT_HoaDon;
import BEAN.HoaDon;
import BEAN.KhachHang;
import BEAN.NhanVien;
import BEAN.Thuoc;
import BEAN.format;
import DAO.DAO_KhachHang;
import DAO.DAO_Thuoc;
import view.DialogThemKhachHang.DialogThemKhachHangResponse;

public class CuaHang_Pn extends JPanel implements ActionListener,
		MouseListener, KeyListener, ChangeListener {
	String pattern = "###,###.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtThanhPhan;
	private static JTable tableThuoc;
	private DefaultTableModel modelThuoc;
	private JTable tableDanhMuc;
	private JTextField txtTimSDT;
	private JTextField txtTienNhan;
	private JTextField txtTenThuoc_pnCuaHang;
	private JScrollPane scrollPane;
	private JComboBox cbDonViTinh_pnCuaHang;
	private JComboBox cbCongDung_pnCuaHang;
	private JTextField txtSoLuongThem;
	private DefaultTableModel tableModelThuoc;
	private DefaultTableModel tableModelDanhMuc;
	private JButton btnThem;
	private JLabel lblTenKhachHang;
	private JButton btnThemKhachHang;
	private JLabel lblTongTien;
	private JLabel lblTienThoi;
	private JButton btnHuyDon;
	private JButton btnThanhToan;
	private JLabel lblKiemTraTienNhap;
	private JButton btnXemChiTiet;
	private JButton btnLuiCuoi;
	private JButton btnLui1;
	private JButton btnTien1;
	private JButton btnTienCuoi;
	private JButton btnLamMoiTimKiem;
	private JLabel lblSoTrang;
	private JButton btnTimSDT;
	private ArrayList<Thuoc> listDanhMucThuoc;
	private JLabel lblnVTnh;
	
	/**
	 * Create the panel.
	 */
	public CuaHang_Pn() {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(1310, 599);

		JLabel lblNewLabel = new JLabel("T\u00EAn thu\u1ED1c: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(88, 11, 117, 30);
		add(lblNewLabel);

		JLabel lblHotCht = new JLabel("Hoạt chất:");
		lblHotCht.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHotCht.setBounds(720, 11, 117, 30);
		add(lblHotCht);

		txtTenThuoc_pnCuaHang = new JTextField();
		txtTenThuoc_pnCuaHang.setBounds(202, 11, 400, 30);
		add(txtTenThuoc_pnCuaHang);
		txtTenThuoc_pnCuaHang.setColumns(10);

		JLabel lblHotCht_2 = new JLabel("C\u00F4ng d\u1EE5ng: ");
		lblHotCht_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHotCht_2.setBounds(721, 47, 117, 30);
		add(lblHotCht_2);

		JLabel lblHotCht_3 = new JLabel("ĐVTính: ");
		lblHotCht_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHotCht_3);

		cbDonViTinh_pnCuaHang = new JComboBox();
		cbDonViTinh_pnCuaHang.setBounds(202, 47, 400, 30);
		add(cbDonViTinh_pnCuaHang);

		cbCongDung_pnCuaHang = new JComboBox();
		cbCongDung_pnCuaHang.setBounds(824, 47, 400, 30);
		add(cbCongDung_pnCuaHang);

		txtThanhPhan = new JTextField();
		txtThanhPhan.setColumns(10);
		txtThanhPhan.setBounds(824, 11, 400, 30);
		add(txtThanhPhan);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 102));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(0, 366, 1284, 25);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Danh mục thuốc");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(347, 5, 113, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(1009, 5, 113, 14);
		panel_1.add(lblNewLabel_1_1);
		// String title[] = {"Mã thuốc","Tên thuốc", "Thành phần",
		// "Dạng bào chế", "ĐVT", "Hàm lượng", "Công dụng",
		// "Hạn sử dụng","Số lượng","Trạng thái", "Giá (VND)"};
		// modelThuoc = new DefaultTableModel(title,0);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 89, 1284, 240);
		add(scrollPane);
		tableThuoc = new JTable();
		scrollPane.setViewportView(tableThuoc);

		tableThuoc.setModel(tableModelThuoc = new DefaultTableModel(
				new Object[][] {

				}, new String[] { "Mã thuốc", "Tên thuốc", "Thành phần",
						"Dạng bào chế", "ĐVT", "Hàm lượng", "Công dụng",
						"Hạn sử dụng", "Số lượng", "Trạng thái", "Giá (VND)"
				// "T\u00EAn thu\u1ED1c", "Th\u00E0nh ph\u1EA7n",
				// "D\u1EA1ng b\u00E0o ch\u1EBF", "\u0110VT",
				// "H\u00E0m l\u01B0\u1EE3ng", "C\u00F4ng d\u1EE5ng",
				// "H\u1EA1n s\u1EED d\u1EE5ng", "S\u1ED1 l\u01B0\u1EE3ng",
				// "Tr\u1EA1ng th\u00E1i", "Gi\u00E1 (VND)"
				}));
		tableThuoc.setRowHeight(35);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 394, 770, 178);
		add(scrollPane_1);

		tableDanhMuc = new JTable();
		scrollPane_1.setViewportView(tableDanhMuc);
		tableDanhMuc.setModel(tableModelDanhMuc = new DefaultTableModel(
				new Object[][] {}, new String[] { "Tên thuốc", "Thành phần",
						"Hàm lượng", "Số lượng", "Công dụng", "Giá (VNĐ)",
						"Thành tiền" }));
		tableDanhMuc.setRowHeight(32);
		// tableDanhMuc.setEnabled(false);
		JLabel lblTmSt = new JLabel("Tìm SĐT: ");
		lblTmSt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTmSt.setBounds(780, 417, 67, 14);
		add(lblTmSt);

		JLabel lblTnKhchHng = new JLabel("Tên KH: ");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnKhchHng.setBounds(780, 454, 67, 19);
		add(lblTnKhchHng);

		JLabel lblTngTin = new JLabel("Tổng tiền: ");
		lblTngTin.setToolTipText("Đã bao gồm 10% VAT");
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngTin.setBounds(1052, 454, 102, 19);
		add(lblTngTin);

		JLabel lblTinNhn = new JLabel("Tiền nhận: ");
		lblTinNhn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTinNhn.setBounds(782, 488, 89, 29);
		add(lblTinNhn);

		JLabel lblTinThi = new JLabel("Tiền thối: ");
		lblTinThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTinThi.setBounds(1052, 488, 79, 29);
		add(lblTinThi);

		txtTimSDT = new JTextField();
		txtTimSDT.setBounds(862, 412, 175, 30);
		add(txtTimSDT);
		txtTimSDT.setColumns(10);

		txtTienNhan = new JTextField();
		txtTienNhan.setColumns(10);
		txtTienNhan.setBounds(862, 488, 175, 30);
		add(txtTienNhan);

		btnThemKhachHang = new JButton("Thêm KH");
		btnThemKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
		btnThemKhachHang.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/them.png")));
		btnThemKhachHang.setBounds(1117, 412, 117, 30);
		add(btnThemKhachHang);

		lblTenKhachHang = new JLabel("");
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(862, 450, 170, 30);
		add(lblTenKhachHang);

		lblTongTien = new JLabel("");
		lblTongTien.setToolTipText("Đã bao gồm 10% VAT");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTien.setBounds(1153, 450, 141, 29);
		add(lblTongTien);

		lblTienThoi = new JLabel("");
		lblTienThoi.setForeground(Color.RED);
		lblTienThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTienThoi.setBounds(1153, 488, 141, 29);
		add(lblTienThoi);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/them.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(171, 330, 150, 35);
		add(btnThem);

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/description.png")));
		btnXemChiTiet.setFont(new Font("Arial", Font.PLAIN, 14));
		btnXemChiTiet.setBounds(0, 330, 149, 35);
		add(btnXemChiTiet);

		btnLamMoiTimKiem = new JButton("Làm mới tìm kiếm");
		btnLamMoiTimKiem.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/lam_moi.png")));
		btnLamMoiTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLamMoiTimKiem.setBounds(1115, 330, 170, 35);
		add(btnLamMoiTimKiem);

		btnHuyDon = new JButton("Hủy đơn");
		btnHuyDon.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/stopping.png")));
		btnHuyDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHuyDon.setBounds(862, 535, 175, 35);
		add(btnHuyDon);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/pay.png")));
		btnThanhToan.setFont(new Font("Arial", Font.PLAIN, 14));
		btnThanhToan.setBounds(1059, 535, 175, 35);
		add(btnThanhToan);

		txtSoLuongThem = new JTextField();
		txtSoLuongThem.setBounds(370, 330, 60, 35);
		add(txtSoLuongThem);
		txtSoLuongThem.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("SL:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(330, 330, 40, 35);
		add(lblNewLabel_3);

		btnTien1 = new JButton("");
		btnTien1.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/forward-button.png")));
		btnTien1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTien1.setBounds(747, 333, 100, 30);
		add(btnTien1);

		btnLui1 = new JButton("");
		btnLui1.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/rewind-button.png")));
		btnLui1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLui1.setBounds(587, 333, 100, 30);
		add(btnLui1);

		btnTienCuoi = new JButton("");
		btnTienCuoi.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/nextEnd.png")));
		btnTienCuoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTienCuoi.setBounds(857, 333, 100, 30);
		add(btnTienCuoi);

		btnLuiCuoi = new JButton("");
		btnLuiCuoi.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/previousEnd.png")));
		btnLuiCuoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLuiCuoi.setBounds(477, 333, 100, 30);
		add(btnLuiCuoi);

		lblSoTrang = new JLabel("1");
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoTrang.setBounds(687, 333, 60, 28);
		add(lblSoTrang);

		btnTimSDT = new JButton("");
		btnTimSDT.setBackground(Color.WHITE);
		btnTimSDT.setIcon(new ImageIcon(CuaHang_Pn.class
				.getResource("/img/Search.png")));
		btnTimSDT.setBounds(1057, 412, 50, 30);
		add(btnTimSDT);

		lblKiemTraTienNhap = new JLabel("");
		lblKiemTraTienNhap.setBounds(1034, 490, 30, 26);
		add(lblKiemTraTienNhap);

		addItemInToCbDVT();
		addItemInToCbCongDung();

		btnThem.addActionListener(this);
		txtSoLuongThem.addActionListener(this);
		tableThuoc.addMouseListener(this);
		tableDanhMuc.addMouseListener(this);
		txtTimSDT.addActionListener(this);
		txtTienNhan.addKeyListener(this);
		btnLamMoiTimKiem.addActionListener(this);
		btnLui1.addActionListener(this);
		btnLuiCuoi.addActionListener(this);
		btnTien1.addActionListener(this);
		btnTienCuoi.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnHuyDon.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThemKhachHang.addActionListener(this);
		btnHuyDon.addActionListener(this);
		cbCongDung_pnCuaHang.addActionListener(this);
		cbDonViTinh_pnCuaHang.addActionListener(this);
		txtThanhPhan.addKeyListener(this);
		txtTenThuoc_pnCuaHang.addKeyListener(this);
		btnTimSDT.addActionListener(this);
		
		JLabel lblNewLabel_2 = new JLabel("(Đã bao gồm 10% VAT)");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_2.setBounds(1052, 475, 104, 13);
		add(lblNewLabel_2);
		
		lblnVTnh = new JLabel("Đơn vị tính:");
		lblnVTnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnVTnh.setBounds(88, 47, 117, 30);
		add(lblnVTnh);
		listDanhMucThuoc = new ArrayList<Thuoc>();

		// khong cho sua table
		tableThuoc.setDefaultEditor(Object.class, null);
		tableDanhMuc.setDefaultEditor(Object.class, null);
	}
	// lay du lieu tu them khach hang
		public class DialocThemKhachHangInterface implements DialogThemKhachHangResponse{

			@Override
			public void getResponse(KhachHang khachHang) {
				if(khachHang != null) {
					lblTenKhachHang.setText(khachHang.getTenKhachHang());
					txtTimSDT.setText(khachHang.getSoDienThoai());
				}
			}	
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = DB.Connect.CreateConnection();

		int tongPage = 1;
		String tenThuoc = txtTenThuoc_pnCuaHang.getText();
		String thanhPhan = txtThanhPhan.getText();
		String dvt = cbDonViTinh_pnCuaHang.getSelectedIndex() > 0 ? cbDonViTinh_pnCuaHang
				.getSelectedItem().toString() : "";
		String congDung = cbCongDung_pnCuaHang.getSelectedIndex() > 0 ? cbCongDung_pnCuaHang
				.getSelectedItem().toString() : "";
		int tongHang = DAO_Thuoc.tongHang(con, tenThuoc, congDung, dvt,
				thanhPhan);
		tongPage = tongHang % 20 == 0 ? tongHang / 20 : (tongHang / 20) + 1;
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem) || o.equals(txtSoLuongThem)) {
//			if(tableThuoc.getSelectedRow() == -1) {
//				JOptionPane.showMessageDialog(this,"Bạn chưa chọn Thuốc");
//				return;
//			}
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				if (kiemTraThemVaoGioHang()) {
					int row = tableThuoc.getSelectedRow();
					if(tableThuoc.getValueAt(row, 9).toString().equalsIgnoreCase("Ngung Kinh Doanh")) {
						JOptionPane.showMessageDialog(this,"Thuốc đã ngừng kinh doanh");
						return;
					}
//					System.out.println(Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(0,4)));
//					System.out.println(Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(5,7)));
//					System.out.println(Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(8,10)));
//					System.err.println(LocalDate.of(Integer.parseInt(tableThuoc.getValueAt(row, 9).toString().substring(0,3)), Integer.parseInt(tableThuoc.getValueAt(row, 9).toString().substring(5,6)),Integer.parseInt(tableThuoc.getValueAt(row, 9).toString().substring(8,9))));
					if(LocalDate.now().isAfter(LocalDate.of(Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(0,4)), Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(5,7)),Integer.parseInt(tableThuoc.getValueAt(row, 7).toString().substring(8,10))))) {
						JOptionPane.showMessageDialog(this,"Thuốc đã hết hạn");
						return;
					}
					double donGia =(format.chuyenDoiNguocLaiTienTe(tableThuoc.getValueAt(
							row, 10).toString()));
					int soLuong = Integer.parseInt(txtSoLuongThem.getText().trim());

					for (int i = 0; i < tableModelDanhMuc.getRowCount(); i++) {
						if (tableThuoc.getValueAt(row, 0).toString()
								.equals(listDanhMucThuoc.get(i).getMaThuoc())) {// neu
																				// thuoc
																				// them
																				// vao
																				// da
																				// co
																				// trong
																				// gio
																				// hang
							int gopSoLuongThem = Integer
									.parseInt(txtSoLuongThem.getText().trim())
									+ Integer.parseInt(tableDanhMuc.getValueAt(
											i, 3).toString());// tinh so luong
																// gop(cu + moi)
							tableDanhMuc.setValueAt(
									Integer.toString(gopSoLuongThem), i, 3);// set
																			// lai
																			// so
																			// luong
																			// thuoc
																			// vua
																			// them
																			// them
																			// trong
																			// gio
																			// hang
							double thanhTien = format.chuyenDoiNguocLaiTienTe(tableDanhMuc
									.getValueAt(i, 5).toString())
									* Integer.parseInt(tableDanhMuc.getValueAt(
											i, 3).toString());// tinh thanh tien
							tableDanhMuc.setValueAt(thanhTien, i, 6);// set lai
																		// cot
																		// thanh
																		// tien
																		// trong
																		// gio
																		// hang
							int soLuongHienTai = Integer.parseInt(tableThuoc
									.getValueAt(row, 8).toString());// lay so
																	// luong
																	// thuoc
																	// hien tai
																	// tren
																	// table
																	// thuoc
							tableThuoc.setValueAt(
									Integer.toString(soLuongHienTai - soLuong),
									row, 8);// set lai so luong thuoc tren ban
											// table thuoc khi them thuoc vao
											// gio hang
							listDanhMucThuoc.get(i).setSoLuongBanDau(
									gopSoLuongThem);// set lai thuoc tinh so
													// luong thuoc trong danh
													// sach danh muc thuoc

							double tongTien = tinhTongThanhTien();// tinh tong
																	// tien
							lblTongTien.setText(format.chuyenDoiTienTe(tongTien));// set
																			// tong
																			// tien
							return;
						}
					}

					double thanhTien = donGia * soLuong;
					tableModelDanhMuc.addRow(new Object[] {
							tableThuoc.getValueAt(row, 1),
							tableThuoc.getValueAt(row, 2),
							tableThuoc.getValueAt(row, 5), soLuong,
							tableThuoc.getValueAt(row, 6),
							tableThuoc.getValueAt(row, 10).toString(), format.chuyenDoiTienTe(thanhTien) });
					lblTongTien.setText(format.chuyenDoiTienTe(tinhTongThanhTien()));
					tinhTienThoi();
					int soLuongConLai = Integer.parseInt(tableThuoc.getValueAt(
							row, 8).toString())
							- soLuong;
					tableThuoc.setValueAt(Integer.toString(soLuongConLai), row,
							8);
					listDanhMucThuoc.add(new Thuoc(tableThuoc
							.getValueAt(row, 0).toString(),format.chuyenDoiNguocLaiTienTe(tableThuoc.getValueAt(row,10).toString()),Integer
							.parseInt(txtSoLuongThem.getText().trim())));
				}

			} else {

				// xoa
				int index = tableDanhMuc.getSelectedRow();
				if(index == -1){
					JOptionPane.showMessageDialog(this,"Chọn thuốc cần xóa khỏi giỏ hàng");
					return;
				}
				if(kiemTraSoLuongXoa()){
				int xacnhan = JOptionPane.showConfirmDialog(this,
						"Bạn có muốn xóa thuốc này khỏi giỏ hàng không",
						"Thông báo", JOptionPane.YES_NO_OPTION);
				if (xacnhan == JOptionPane.YES_OPTION) {
						if(Integer.parseInt(txtSoLuongThem.getText().trim()) < Integer.parseInt(tableDanhMuc.getValueAt(index,3).toString())){
							int soLuongConLai = Integer.parseInt(tableDanhMuc.getValueAt(index,3).toString()) - Integer.parseInt(txtSoLuongThem.getText().trim());
							tableDanhMuc.setValueAt(Integer.toString(soLuongConLai), index,3);
							double tienConLai = soLuongConLai*format.chuyenDoiNguocLaiTienTe(tableDanhMuc.getValueAt(index,5).toString());
							tableDanhMuc.setValueAt(Double.toString(tienConLai),index,6);
							listDanhMucThuoc.get(index).setSoLuongBanDau(soLuongConLai);
							for(int i=0;i<tableModelThuoc.getRowCount();i++){
								if(tableThuoc.getValueAt(i,0).toString().equals(listDanhMucThuoc.get(index).getMaThuoc())){
									int soLuongCapNhat = Integer.parseInt(tableThuoc.getValueAt(i,8).toString()) + Integer.parseInt(txtSoLuongThem.getText().trim());
									tableThuoc.setValueAt(Integer.toString(soLuongCapNhat),i,8);
								}
							}
							
						}
						else if(Integer.parseInt(txtSoLuongThem.getText().trim()) == Integer.parseInt(tableDanhMuc.getValueAt(index,3).toString())){							
							tableModelDanhMuc.removeRow(index);
							
							for(int i=0;i<tableModelThuoc.getRowCount();i++){
								if(tableThuoc.getValueAt(i,0).toString().equals(listDanhMucThuoc.get(index).getMaThuoc())){
									int soLuongCapNhat = Integer.parseInt(tableThuoc.getValueAt(i,8).toString()) + Integer.parseInt(txtSoLuongThem.getText().trim());
									tableThuoc.setValueAt(Integer.toString(soLuongCapNhat),i,8);
								}
							}
							listDanhMucThuoc.remove(index);
						}
						lblTongTien.setText(decimalFormat.format(tinhTongThanhTien()));
					}
				}
			}

		}

		if (o.equals(btnXemChiTiet)) {

			int row = tableThuoc.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this,
						"Vui lòng chọn thuốc cần xem chi tiết");

			} else {
				String maThuoc = tableThuoc.getValueAt(row, 0).toString();
				ChiTietThuoc_JFrame chiTietThuoc_JFrame = new ChiTietThuoc_JFrame(
						maThuoc);
				chiTietThuoc_JFrame.setVisible(true);
			}

		}

		if (o.equals(btnLamMoiTimKiem)) {
			lamMoi();
		}

		if (o.equals(btnTien1)) {

			int page = Integer.parseInt(lblSoTrang.getText()) + 1;

			if (page <= tongPage) {
				lblSoTrang.setText(Integer.toString(page));
				xoaToanBoban();
				tableThuoc.clearSelection();
				addInToTable();
//				upDateSoLuongThuoc();
			}

		}
		if (o.equals(btnLui1)) {
			int page = Integer.parseInt(lblSoTrang.getText()) - 1;
			if (page >= 1) {
				lblSoTrang.setText(Integer.toString(page));
				xoaToanBoban();
				tableThuoc.clearSelection();
				addInToTable();

//				upDateSoLuongThuoc();
			}
		}

		if (o.equals(btnTienCuoi)) {
			int page = Integer.parseInt(lblSoTrang.getText());
			if (page != tongPage) {
				lblSoTrang.setText(Integer.toString(tongPage));
				xoaToanBoban();
				tableThuoc.clearSelection();
				addInToTable();
//				upDateSoLuongThuoc();
			}
		}

		if (o.equals(btnLuiCuoi)) {
			int page = Integer.parseInt(lblSoTrang.getText());
			if (page != 1) {
				lblSoTrang.setText(Integer.toString(1));
				xoaToanBoban();
				tableThuoc.clearSelection();
				addInToTable();
//				upDateSoLuongThuoc();
			}
		}

		if (o.equals(cbCongDung_pnCuaHang) || o.equals(cbDonViTinh_pnCuaHang)) {
			lblSoTrang.setText("1");
			xoaToanBoban();
			addInToTable();
//			upDateSoLuongThuoc();
		}
		if (o.equals(btnTimSDT) || o.equals(txtTimSDT)) {
			String txtSoDienThoai = txtTimSDT.getText();
			if (txtTimSDT.getText().equals("")) {
				showMess(txtTimSDT, "Vui lòng nhập số điện thoại cần tìm");
			} else {
				Connection conn = DB.Connect.CreateConnection();
				KhachHang khachHang = DAO_KhachHang.layThongTinKhachHangQuaSDT(
						conn, txtSoDienThoai);
				if (khachHang == null) {
					int xacNhan = JOptionPane
							.showConfirmDialog(
									this,
									"Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không",
									"Thông báo", JOptionPane.YES_NO_OPTION);
					if (xacNhan == JOptionPane.YES_OPTION) {
						new DialogThemKhachHang(new DialocThemKhachHangInterface()).setVisible(true);
					}
					else{
						lblTenKhachHang.setText("");
					}

				} else {
					lblTenKhachHang.setText(khachHang.getTenKhachHang());
				}
			}
		}
		if (o.equals(btnThemKhachHang)) {
			new DialogThemKhachHang(new DialocThemKhachHangInterface()).setVisible(true);
		}
		if(o.equals(btnHuyDon)){
			huyDon();
		}
		
		if(o.equals(btnThanhToan)){
			String sdtKH = txtTimSDT.getText();
			String tienNhan = txtTienNhan.getText();
			if(kiemTraThanhToan()){
				
				if(lblTenKhachHang.getText().equals("")){
					int xacNhan = JOptionPane.showConfirmDialog(this,"Tên khách hàng không có trong hệ thống, bạn có muốn thêm khách hàng không","Thông báo",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION){
						DialogThemKhachHang dialogThemKhachHang = new DialogThemKhachHang(new DialocThemKhachHangInterface());
						dialogThemKhachHang.setVisible(true);
					}
				}
//			if(sdtKH.length() > 0 && tienNhan.length() >0 ) {
				KhachHang khachHang = DAO_KhachHang.layThongTinKhachHangQuaSDT(con, sdtKH);
//				if(khachHang == null) {
//					JOptionPane.showMessageDialog(this,"Khach Hang khong ton tai");
//					return;
//				}
				NhanVien nhanVien = GUI_Chinh_JFrame.nhanVien; // lay nhan vien tu trang dang nhap
				HoaDon hoaDon = new HoaDon();
				hoaDon.setMaHoaDonBan(DAO.DAO_HoaDon.phatSinhMaTuDong(con));
				hoaDon.setKhachHang(khachHang);
				hoaDon.setNhanVien(nhanVien);
				hoaDon.setTienNhan(Double.parseDouble(txtTienNhan.getText()));
				hoaDon.setNgayLapHDBan(LocalDate.now().toString());
				ArrayList<CT_HoaDon> list = new ArrayList<CT_HoaDon>();
				for (Thuoc thuoc : listDanhMucThuoc) {
					CT_HoaDon ct_HoaDon = new CT_HoaDon(thuoc.getSoLuongBanDau(),hoaDon,thuoc,thuoc.getGiaBan(),thuoc.getThue());
					list.add(ct_HoaDon);
				}
				if(DAO.DAO_HoaDon.themHoaDon(con, list)) {
					
					JOptionPane.showMessageDialog(this,"Thanh toan Thanh cong");
					 huyDon();
					huyDanhMucThuoc();
					lamMoi();
					tableDanhMuc.clearSelection();
				}
			}
		}

	}
	
	private void huyDon() {
		tableModelDanhMuc.getDataVector().removeAllElements();
		tableModelDanhMuc.fireTableDataChanged();
		txtTimSDT.setText("");
		txtTienNhan.setText("");
		lblTongTien.setText("");
		lblTienThoi.setText("");
		lblTenKhachHang.setText("");
		listDanhMucThuoc = new ArrayList<Thuoc>();
		xoaToanBoban();
		addInToTable();
	}

	public boolean kiemTraThanhToan(){
		if(listDanhMucThuoc.size() == 0) {
			JOptionPane.showMessageDialog(this,"Giỏ hàng hiện tại không có thuốc, Vui lòng thêm thuốc cần mua");
			return false;
		}
		if(txtTimSDT.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại của khách hàng để kiểm tra khách hàng đã có trong hệ thống chưa? ");
			txtTimSDT.requestFocus();
			return false;
		}
		else if(!txtTimSDT.getText().matches("(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})")){
			JOptionPane.showMessageDialog(this,"Số điện thoại không hợp lệ");
			txtTimSDT.requestFocus();
			txtTimSDT.selectAll();
			return false;
		}
		if(txtTienNhan.getText().equals("")){
			JOptionPane.showMessageDialog(this,"Vui lòng nhập số tiền khách hàng thanh toán");
			txtTienNhan.requestFocus();
			return false;
		}
		else if(kiemTraTienNhap() == false){
			JOptionPane.showMessageDialog(this,"Tiền nhận không hợp lệ");
			txtTienNhan.requestFocus();
			txtTienNhan.selectAll();
			return false;
		}
		return true;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		int row = tableDanhMuc.getSelectedRow();
		if (object.equals(tableDanhMuc)) {
			btnThem.setText("Xóa");
			btnThem.setIcon(new ImageIcon(CuaHang_Pn.class
					.getResource("/img/xoa.png")));
			txtSoLuongThem.setText(tableDanhMuc.getValueAt(row, 3).toString());
			txtSoLuongThem.requestFocus();
		} else if (object.equals(tableThuoc)) {
			btnThem.setText("Thêm");
			btnThem.setIcon(new ImageIcon(CuaHang_Pn.class
					.getResource("/img/them.png")));
		}

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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(txtTienNhan)) {
			tinhTienThoi();
		}
		if (o.equals(txtThanhPhan) || o.equals(txtTenThuoc_pnCuaHang)) {
			lblSoTrang.setText("1");
			xoaToanBoban();
			addInToTable();
//			upDateSoLuongThuoc();
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void addInToTable() {
	
		Connection con = DB.Connect.CreateConnection();
		int page = Integer.parseInt(lblSoTrang.getText());
		String tenThuoc = txtTenThuoc_pnCuaHang.getText();
		String thanhPhan = txtThanhPhan.getText();

		String dvt = cbDonViTinh_pnCuaHang.getSelectedIndex() > 0 ? cbDonViTinh_pnCuaHang
				.getSelectedItem().toString() : "";
		String congDung = cbCongDung_pnCuaHang.getSelectedIndex() > 0 ? cbCongDung_pnCuaHang
				.getSelectedItem().toString() : "";

		ArrayList<Thuoc> list = DAO_Thuoc.danhSachThuoc(con, page - 1,
				tenThuoc, thanhPhan, dvt, congDung, "", "", "");
		if (list.size() == 0) {
			// JOptionPane.showMessageDialog(this, "Danh sách rỗng");
			// clear();
			tableModelThuoc.fireTableDataChanged();
			return;
		}
		for (Thuoc thuoc : list) {
			tableModelThuoc.addRow(new Object[] {
					thuoc.getMaThuoc(),
					thuoc.getTenThuoc(),
					thuoc.getThanhPhan(),
					thuoc.getDangBaoChe(),
					thuoc.getDonViTinh(),
					thuoc.getHamLuong(),
					thuoc.getCongDung().getCongDung(),
					thuoc.getHanSuDung().toString(),
					thuoc.getSoLuongBanDau(),
					thuoc.isTrangThaiKinhDoanh() ? "Dang kinh doanh"
							: "Ngung kinh doanh",format.chuyenDoiTienTe(thuoc.getGiaBan())});
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		upDateSoLuongThuoc();

	}

	public void addItemInToCbDVT() {
		Connection con = DB.Connect.CreateConnection();
		ArrayList<String> listDVT = DAO_Thuoc.getDonViTinh(con);
		for (String item : listDVT) {
			cbDonViTinh_pnCuaHang.addItem(item);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showMess(JTextField txt, String mess) {
		JOptionPane.showMessageDialog(this, mess);
		txt.requestFocus();
		txt.selectAll();
	}

	public void addItemInToCbCongDung() {
		Connection con = DB.Connect.CreateConnection();
		ArrayList<String> listCongDung = DAO_Thuoc.getCongDung(con);
		for (String item : listCongDung) {
			cbCongDung_pnCuaHang.addItem(item);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isNum(String txt) {
		try {
			Integer.parseInt(txt);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean kiemTraThemVaoGioHang() {
		int row = tableThuoc.getSelectedRow();
		try {
			if (row == -1) {
				
				JOptionPane.showMessageDialog(this,
						"Vui lòng chọn thuốc cần thêm");
				return false;
			}
			if (txtSoLuongThem.getText().equals("")) {
				showMess(txtSoLuongThem, "Vui lòng nhập số số lượng cần thêm");
				return false;
			} else {

				if (!isNum(txtSoLuongThem.getText().trim())
						|| Integer.parseInt(txtSoLuongThem.getText().trim()) <= 0) {
					showMess(txtSoLuongThem,
							"Số lượng thuốc thêm vào phải là số nguyên dương");
					return false;
				}
				if (Integer.parseInt(txtSoLuongThem.getText().trim()) > Integer
						.parseInt(tableThuoc.getValueAt(row, 8).toString())) {
					showMess(txtSoLuongThem,
							"Số lượng thuốc thêm vượt quá số lượng thuốc hiện có");
					return false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			showMess(txtSoLuongThem, "Số lượng thuốc phải là số nguyên");
			return false;

		}
		return true;

	}

	public void fillInText() {
		int row = tableThuoc.getSelectedRow();
		txtTenThuoc_pnCuaHang.setText(tableThuoc.getValueAt(row, 1).toString());
		txtThanhPhan.setText(tableThuoc.getValueAt(row, 2).toString());
		cbDonViTinh_pnCuaHang.setSelectedItem(tableThuoc.getValueAt(row, 4));
		cbCongDung_pnCuaHang.setSelectedItem(tableThuoc.getValueAt(row, 6));
	}

	public double tinhTongThanhTien() {
		double tongThanhTien = 0;
		for (int i = 0; i < tableModelDanhMuc.getRowCount(); i++) {
			tongThanhTien += format.chuyenDoiNguocLaiTienTe(tableDanhMuc.getValueAt(i, 6)
					.toString());
		}
		return tongThanhTien +tongThanhTien*0.1 ;
	}

	public boolean isNumReal(JTextField txt) {
		try {
			Double.parseDouble(txt.getText());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean kiemTraTienNhap() {
		if (!isNumReal(txtTienNhan)) {
			return false;
		} else {
			if (Double.parseDouble(txtTienNhan.getText()) <= 0
					|| Double.parseDouble(txtTienNhan.getText()) < tinhTongThanhTien()) {
				return false;
			}
		}
		return true;
	}

	public void tinhTienThoi() {
		if(txtTienNhan.getText().equals("")){
			lblKiemTraTienNhap.setIcon(new ImageIcon(CuaHang_Pn.class
					.getResource("/img/white.png")));
		}
		else{
			
			if (kiemTraTienNhap()) {
				double tienThoi = 0;
				lblKiemTraTienNhap.setIcon(new ImageIcon(CuaHang_Pn.class
						.getResource("/img/true.png")));
				double tienNhan = Double.parseDouble(txtTienNhan.getText());
				tienThoi = tienNhan - tinhTongThanhTien();
				lblTienThoi.setText(format.chuyenDoiTienTe(tienThoi));
			} else {
				lblKiemTraTienNhap.setIcon(new ImageIcon(CuaHang_Pn.class
						.getResource("/img/false.png")));
				lblTienThoi.setText("");
			}
		}
		
	}

	public void lamMoi() {
		txtSoLuongThem.setText("");
		txtTenThuoc_pnCuaHang.setText("");
		txtThanhPhan.setText("");
		txtTimSDT.setText("");
		cbCongDung_pnCuaHang.setSelectedIndex(0);
		cbDonViTinh_pnCuaHang.setSelectedIndex(0);
		tableThuoc.clearSelection();
	}

	public void clear() {

		txtTenThuoc_pnCuaHang.setText("");
		txtThanhPhan.setText("");
		cbCongDung_pnCuaHang.setSelectedIndex(0);
		cbDonViTinh_pnCuaHang.setSelectedIndex(0);
		tableThuoc.clearSelection();

	}

	public void upDateSoLuongThuoc() {

		for (Thuoc thuoc : listDanhMucThuoc) {
			String maThuoc = thuoc.getMaThuoc();

			for (int i = 0; i < tableModelThuoc.getRowCount(); i++) {
				if (tableThuoc.getValueAt(i, 0).toString().equals(maThuoc)) {

					int soLuongThuocConLai = Integer.parseInt(tableThuoc
							.getValueAt(i, 8).toString())
							- thuoc.getSoLuongBanDau();

					tableThuoc.setValueAt(Integer.toString(soLuongThuocConLai),
							i, 8);

				}
			}
		}
	}

	public boolean kiemTraSoLuongXoa(){
		String txtSL = txtSoLuongThem.getText().trim();
		int row = tableDanhMuc.getSelectedRow();
		if(txtSL.equals("") || txtSL.equals("0")){
			showMess(txtSoLuongThem, "Nhập số lượng thuốc cần xóa");
			return false;
		}
		if(!isNum(txtSL) || Integer.parseInt(txtSL) <0){
			showMess(txtSoLuongThem, "Số lượng xóa là số nguyên dương");
			return false;
		}
		if(Integer.parseInt(txtSL)>Integer.parseInt(tableDanhMuc.getValueAt(row, 3).toString())){
			showMess(txtSoLuongThem, "Số lượng xóa không được vượt quá số lượng thuốc đang được chọn");
			return false;
		}
		return true;
	}

	public void xoaToanBoban() {
		tableModelThuoc.getDataVector().removeAllElements();
		tableDanhMuc.clearSelection();
	}
	
public void huyDanhMucThuoc() {
		
//		tableModelDanhMuc.getDataVector().removeAllElements();
//		tableDanhMuc.removeAll();
//		listDanhMucThuoc.clear();	
		tableModelDanhMuc.getDataVector().removeAllElements();
		tableModelDanhMuc.fireTableDataChanged();
		listDanhMucThuoc.clear();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void khoiTaoDuLieu() {
	 xoaToanBoban();
	 addInToTable();
		
	}
}
