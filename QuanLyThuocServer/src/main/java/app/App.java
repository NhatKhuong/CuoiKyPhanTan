package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

public class App {
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
//			QuanLyDao quanLyDao = new QuanLyImpl();

			LocateRegistry.createRegistry(9999);
			Context context = new InitialContext();
//			Naming.bind("rmi://192.168.1.7:9999/quanLyDao", quanLyDao);
			System.out.println("Server bound in RMIRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
