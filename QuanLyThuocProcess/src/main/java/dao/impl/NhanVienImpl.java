package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

	public List<NhanVien> DanhSachNhanVien(int page, String txtSearch,String gioiTinh, String trangThaiLamViec){
	Session session = sessionFactory.openSession();
	Transaction tr = session.getTransaction();
	if (txtSearch == null)
		txtSearch = "";
	if (gioiTinh == null)
		gioiTinh = "";
	String ttlv = "";
	if(trangThaiLamViec != null ) {
		ttlv  = trangThaiLamViec;
	}

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

	public int tongHang(String txtSearch, String trangThaiLamViec) throws RemoteException {
		
		return 20;
	}

	public String phatSinhMaTuDong() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
//			KhachHang khachHang =session.createQuery("select * from KhachHang where soDienThoai = :sdt",KhachHang.class).setParameter("sdt", sdt).getSingleResult();
			tr.begin();
			session.save(nhanVien);
			tr.commit();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}

	public boolean suaCmnd(String maNhanVien, String cmnd) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean suaNhanVien(NhanVien nhanVien) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update("NhanVien", nhanVien);
			tr.commit();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}

	public boolean suaTrangThaiLamViec(String maNhanVien, boolean trangThaiLamViec) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = "UPDATE NhanVien set trangThaiLamViec = :trangThaiLamViec WHERE maNhanVien = :maNhanVien ";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createQuery(sql);

			query.setParameter("trangThaiLamViec", trangThaiLamViec);
			query.setParameter("maNhanVien", maNhanVien);
			int result = query.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}

	public boolean kiemTraSoDienThoai(String sdt) throws RemoteException {
		return layThongTinNhanVienQuaSDT(sdt) == null ? true : false;
	}

	public boolean kiemTraSoChungMinhNhanDan(String cmnd) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from nhanVien where cmnd = '" + cmnd + "'";
			NhanVien nhanVien = session.createNativeQuery(sql, NhanVien.class).getSingleResult();
			tr.commit();
			if (nhanVien == null) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}

	public String layMaNhanVienQuaSoDienThoai(String sdt) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean xoaNhanVien(String maNhanVien) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}
