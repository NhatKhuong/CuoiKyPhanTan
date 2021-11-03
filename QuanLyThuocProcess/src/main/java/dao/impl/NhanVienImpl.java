package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhanVienDao;
import entity.NhanVien;
import util.HibernateUtil;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao {
	private static int limit = 20;
	private SessionFactory sessionFactory;

	public NhanVienImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		sessionFactory = new HibernateUtil().getSessionFactory();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<NhanVien> DanhSachNhanVien(int page, String txtSearch,String gioiTinh, boolean trangThaiLamViec){
	Session session = sessionFactory.openSession();
	Transaction tr = session.getTransaction();
	if (txtSearch == null)
		txtSearch = "";
	if (gioiTinh == null)
		gioiTinh = "";
	String ttlv = trangThaiLamViec ? "0" : "1";
	int offset = page * limit;// lay du lieu bat dau tu vi tri page*20

	try {
		tr.begin();

		String sql = "select * from NhanVien inner join DiaChi on  NhanVien.maDC = DiaChi.maDC where tenNhanVien like N'%"
				+ txtSearch + "%'  and trangThaiLamViec like '%" + ttlv + "%' " + " order by maNhanVien desc"
				+ " OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY;";

//		"from ModelClassname where ClassVariableId= :ClassVariableId"
//		Query q = session.createQuery(sql);
//		q.setParameter("ClassVariableId", 001);

		List<NhanVien> dsNhanVien = session.createNativeQuery(sql, NhanVien.class).getResultList();

//		KhachHang khachHang = session.
		tr.commit();

		return dsNhanVien;
	} catch (Exception e) {
		// TODO: handle exception
		tr.rollback();

	} finally {
		session.close();
	}
	return null;
	}

	@Override
	public NhanVien layThongTinNhanVienQuaSDT(String sdt) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from nhanVien where soDienThoaiNV = '"+sdt+"'";

			NhanVien nhanVien = session.createNativeQuery(sql , NhanVien.class).getSingleResult();

			tr.commit();

			return nhanVien;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int tongHang(String txtSearch, String trangThaiLamViec) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String phatSinhMaTuDong() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suaCmnd(String maNhanVien, String cmnd) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suaNhanVien(NhanVien nhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suaTrangThaiLamViec(String maNhanVien, boolean trangThaiLamViec) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean kiemTraSoDienThoai(String sdt) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean kiemTraSoChungMinhNhanDan(String cmnd) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dangNhap(String sdt, String pass) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from nhanVien where soDienThoaiNV = '"+sdt+"' and passLogin = '"+pass+"'" ;
			NhanVien nhanVien = session.createNativeQuery(sql , NhanVien.class).getSingleResult();
			tr.commit();
			if(nhanVien != null) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public String layMaNhanVienQuaSoDienThoai(String sdt) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean xoaNhanVien(String maNhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
