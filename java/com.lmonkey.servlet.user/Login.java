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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		response.setContentType("text/html;charset=gb2312");
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		int count = LMONKEY_USERDao.selectByNM(userName, passWord);
		
		if(count > 0) {
			HttpSession session = request.getSession();
			LMONKEY_USER user = LMONKEY_USERDao.selectAdmin(userName, passWord);
			
			
			
			session.setAttribute("name", user);
            session.setAttribute("isLogin", "1");			
			response.sendRedirect("index.jsp");
			
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('用户登录失败');");
			out.write("location.href='login.jsp'");
			out.write("</script>");
		}
	}

}
