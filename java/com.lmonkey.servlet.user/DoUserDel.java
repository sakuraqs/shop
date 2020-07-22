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
 * Servlet implementation class DoUserDel
 */
@WebServlet("/manage/admin_douserdel")
public class DoUserDel extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		String id=request.getParameter("id");
		
	   			
	
		//加入到数据库的用户表中
		int count = LMONKEY_USERDao.del(id);
		//成功或失败的去向
		if(count > 0) {
			response.sendRedirect("/shop/manage/admin_douserselect?cp="+request.getParameter("cpage"));//一开始地址没写全
			
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alter('用户删除失败')");
			out.write("location.href='manger/admin_douserselect?cp="+request.getParameter("cpage")+"'");
			out.write("</script>");
		}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		String ids[]=request.getParameterValues("id[]");
		
	   			
	for(int i=0; i<ids.length; i++) {
		
		LMONKEY_USERDao.del(ids[i]);
	}
			response.sendRedirect("/shop/manage/admin_douserselect");
			
		}

}
