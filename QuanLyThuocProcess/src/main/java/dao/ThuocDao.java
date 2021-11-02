package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Thuoc;

public interface ThuocDao extends Remote{
	public  List<Thuoc> danhSachThuoc( int page, String tenThuoc, String thanhPhan,	String dvt, String congDung, String nhomCongDung, String dangBaoChe, String nuoc) throws RemoteException;
	
	public List<Thuoc> danhSachThuoc( int page) throws RemoteException;
	
	public List<Thuoc> danhSachThuocTheoTen( int page, String txtSearchTen) throws RemoteException;
		
	public List<Thuoc> danhSachThuocTheoThanhPhan( int page, String txtSearchThanhPhan) throws RemoteException;
		
	public List<Thuoc> danhSachThuocTheoDVT( int page, String cbDVTItem) throws RemoteException;
		
	public List<Thuoc> danhSachThuocTheoCongDung( int page, String cbcongdungtem) throws RemoteException;
		
	public List<Thuoc> danhSachThuocTheoThanhPhan_DVT( int page, String thanhphan,	String cbDVTItem) throws RemoteException;
		
	public List<Thuoc> danhSachThuocTheoCongDung_DVT( int page, String cbcongdungItem,	String cbDVTItem) throws RemoteException;
		
	public  List<String> getDonViTinh() throws RemoteException;
		
	public  List<String> getCongDung() throws RemoteException;
		
	public  Thuoc getThuocTheoMa( String maThuoc) throws RemoteException;
		
	public  int tongHang( String tenThuoc, String congDung, String dvt, String thanhPhan) throws RemoteException;
		
	public  int tongHangDVT( String cbDVTItem) throws RemoteException;
		
	public  int tongHangCongDung( String cbCongDung) throws RemoteException;
		
	public  int tongHangCongDungDVT( String cbCongDung, String cbDVTItem) throws RemoteException;
		
	public  boolean setNgungBan( boolean trangThai, String maThuoc) throws RemoteException;
		
	public  boolean capNhatThuoc( String ma, Thuoc thuoc) throws RemoteException;

}
