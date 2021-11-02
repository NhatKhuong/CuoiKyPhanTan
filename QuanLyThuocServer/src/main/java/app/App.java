package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.hibernate.SessionFactory;

import dao.KhachHangDao;
import dao.impl.KhachHangImpl;
import util.HibernateUtil;

public class App {

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

			LocateRegistry.createRegistry(9999);
			Context context = new InitialContext();
//			Naming.bind("rmi://192.168.1.7:9999/quanLyDao", quanLyDao);
			Naming.bind("rmi://192.168.1.7:9999/khachHangDao", khachHangDao);
			System.out.println("Server bound in RMIRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
