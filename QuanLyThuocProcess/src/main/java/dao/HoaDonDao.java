package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CT_HoaDon;
import entity.HoaDon;

public interface HoaDonDao extends Remote{
	public  List<HoaDon> timHoaDon( String maHD,String tenKH,String sdt_KH,String sdt_NV,String ngayLapHD,int cmb,int page) throws RemoteException;
	
    public  int getCount( String maHD,String tenKH,String sdt_KH,String sdt_NV,String ngayLapHD,int cmb) throws RemoteException;
    
    public  String phatSinhMaTuDong() throws RemoteException;
        
    public  boolean themHoaDon(List<CT_HoaDon> list_CT_HoaDon) throws RemoteException;
        
    public  HoaDon getHoaDonTheoMa( String maHD) throws RemoteException;

}
