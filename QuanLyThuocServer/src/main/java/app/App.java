package app;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.hibernate.SessionFactory;

import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.impl.KhachHangImpl;
import dao.impl.NhanVienImpl;
import util.HibernateUtil;

public class App{

	public static void main(String[] args) {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
//			QuanLyDao quanLyDao = new QuanLyImpl();
			KhachHangDao khachHangDao = new KhachHangImpl();
			NhanVienDao nhanVienDao = new NhanVienImpl();
//			khachHangDao.danhSachKhachHang(0,"","")
//			.forEach(item->{
//				System.out.println(item);
//			});
			InetAddress inetAddress = InetAddress.getLocalHost();
			String ip = inetAddress.getHostAddress();
			LocateRegistry.createRegistry(9997);
			Context context = new InitialContext();
//			Naming.bind("rmi://192.168.1.2:9998/quanLyDao", quanLyDao);
			Naming.bind("rmi://"+ip+":9997/khachHangDao", khachHangDao);
			Naming.bind("rmi://"+ip+":9997/nhanVienDao", nhanVienDao);
			System.out.println("Server bound in RMIRegistry at "+ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
