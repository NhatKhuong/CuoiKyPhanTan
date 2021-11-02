package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.KhachHangDao;
import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangImpl extends UnicastRemoteObject implements KhachHangDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private static final int limit = 20;
	

	public KhachHangImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		
		this.sessionFactory = HibernateUtil.getIntance().getSessionFactory();
	}
	@Override
	public String phatSinhMaKhachHang() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhachHang> danhSachKhachHang(int page, String txtSearch, String gioiTinh) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";
		if (gioiTinh == null)
			gioiTinh = "";
		int offset = page * limit ;// lay du lieu bat dau tu vi tri page*20

		try {
			tr.begin();

			String sql = "select * from KhachHang inner join DiaChi on  KhachHang.maDC = DiaChi.maDC where tenKhachHang like N'%"
					+ txtSearch + "%' and gioiTinh like '%" + gioiTinh + "%'" + " order by maKhachHang desc "
					+ " OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY";

			List<KhachHang> dsKhachHang = session.createNativeQuery(sql, KhachHang.class).getResultList();

//			KhachHang khachHang = session.
			tr.commit();

			return dsKhachHang;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public KhachHang layThongTinKhachHangQuaSDT(String sdt) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int tongSoHang(String txtSearch, String trangThaiLamViec) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean themKhachHang(KhachHang khachHang) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean kiemTraSoDienThoai(String sdt) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean suaKhachHang(KhachHang khachHang) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
