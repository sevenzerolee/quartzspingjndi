package com.ctsi.quartzspringjndi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static final Logger log = Logger.getLogger(IndexServlet.class.getSimpleName());

	/**
	 * Default constructor.
	 */
	public IndexServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
		//获取ServletContext 再获取 WebApplicationContextUtils  
//        ServletContext servletContext = this.getServletContext();  
//        WebApplicationContext context =   
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);  
//        userServices = (UserServices) context.getBean("dataSourceFromTomcat");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/mysql/jdbc/quartzSpringJndi");
			Connection conn = ds.getConnection();
			
			System.out.println(conn);
			
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = conn.prepareStatement("select 1 from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info(rs.getString(1));
			}
			
		} 
		catch (NamingException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

}
