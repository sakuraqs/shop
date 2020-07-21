package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class DoUserAdd
 */
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		String username=request.getParameter("userName");
		String name=request.getParameter("name");
		String psd=request.getParameter("password");
		String sex=request.getParameter("sex");
		String year=request.getParameter("birthday");
		String email=request.getParameter("Email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		
	  //PrintWriter pw=response.getWriter(); 			//连接数据库 			
		//创建用户实体
		LMONKEY_USER u=new LMONKEY_USER(username, name, psd,sex,year, null, email,mobile,address,1);
		//加入到数据库的用户表中
		int count = LMONKEY_USERDao.insert(u);
		//System.out.print(u);
		//成功或失败的去向
		if(count > 0) {
			response.sendRedirect("/shop/manage/admin_douserselect");//一开始地址没写全
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alter('用户登录失败')");
			out.write("location.href='manger/admin_useradd.jsp'");
			out.write("</script>");
		  
	}
	}

}
