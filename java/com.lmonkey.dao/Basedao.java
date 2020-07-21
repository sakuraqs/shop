package com.lmonkey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Basedao {
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库连接");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getconn() {
		Connection conn=null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DataBaseName=lmonkeyshop","sa","123456789");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("连接成功");
			return conn;
		}
	
	public static int exectuIUD(String sql, Object[] params) {
		int count = 0;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i =0;i<params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(null, ps, conn);
		}
		return count;
	}
	 public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn)
	 {
		if(rs!=null)
			try {
				if(rs!=null)
				rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
		
}
