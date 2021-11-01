package dao;

import java.util.List;

public interface daoDiaChi {
	public List<String> danhSachPhuongXa();
	public List<String> danhSachPhuongXaTheoQuanHuyen(String quanHuyen);
	public List<String> danhSachQuanHuyen();
	public List<String> danhSachQuanHuyenTheoTinh(String tinh);
	public List<String> danhSachTinhTP();
	public List<String> danhSachTinhTPTheoQuanHuyen(String quanHuyen);
	public String getMaDC(String tinhTp, String quanHuyen, String xaPhuong);
	
}
