package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class DoUserUpdate extends HttpServlet {
	/**
	 * @param user 
	 * @param s 
	 * @param user 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				String userStatus=request.getParameter("userStatus");
				int status = 1;
				if(userStatus != null) {
					status = Integer.parseInt(userStatus);
				}
				
			  PrintWriter pw=response.getWriter(); 			//连接数据库 			
				//创建用户实体
				LMONKEY_USER user=new LMONKEY_USER(username, name, psd,sex,year, null, email,mobile,address,1);


				int count = LMONKEY_USERDao.update(user);
				//System.out.print(u);
				//成功或失败的去向
				if(count > 0) {
					response.sendRedirect("/shop/manage/admin_douserselect?cp="+request.getParameter("cpage"));
					
				}else {
					PrintWriter out = response.getWriter();
					out.write("<script>");
					out.write("alter('用户登录失败')");
					out.write("location.href='manage/admin_touserupdate?id="+username+"'");
					out.write("</script>");
				  
			}
		
		
	}

}
