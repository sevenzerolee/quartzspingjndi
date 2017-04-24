package com.ctsi.quartzspringjndi;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Hello world!
 * 
 */
public class App {
	
	public static void main(String[] args) {
		
		
		System.out.println("Hello World!");
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/mysql/jdbc/quartzSpringJndi");
			
			while (true) {
				System.out.println(ds.getConnection());

				Thread.sleep(2000L);
			}
		} 
		catch (NamingException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
