package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html charset=utf-8");
				response.setContentType("text/html;charset=gb2312");
				
				String userName = request.getParameter("userName");
				String passWord = request.getParameter("passWord");
				
				int count = LMONKEY_USERDao.selectByNM(userName, passWord);
				
				if(count > 0) {
					LMONKEY_USER user = LMONKEY_USERDao.selectAdmin(userName, passWord);
				    HttpSession session = request.getSession();
					session.setAttribute("name", user);
					session.setAttribute("isLogin", "1");	
					
				    if(user.getUSER_STATUS() == 2) {
						
		            session.setAttribute("isAdminLogin", "1");	
					response.sendRedirect("/shop/manger/admin_index.jsp");
					
				}else {
					response.sendRedirect("/shop/index.jsp");
					
					
				}
			}else {
				PrintWriter out = response.getWriter();
				out.write("<script>");
				out.write("alert('用户登录失败');");
				out.write("location.href='/shop/manger/login.jsp'");
				out.write("</script>");
			}

			}

		

		
	}


