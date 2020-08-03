package com.lmonkey.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.LMONKEY_USER;
public class LMONKEY_USERDao {
      public static int insert(LMONKEY_USER u) {
    	  String sql = "insert into shopuser values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
    	  Object[] params= {
    			  u.getUSER_ID(),
    			  u.getUSER_NAME(),
    			  u.getUSER_PASSWORD(),
    			  u.getUSER_SEX(),
    			  u.getUSER_BIRTHDAY(),
    			  u.getUSER_IDENITY_CODE(),
    			  u.getUSER_EMAIL(),
    			  u.getUSER_MOBILE(),
    			  u.getUSER_ADDRESS(),
    			  u.getUSER_STATUS()
    			  
    	  };
		return Basedao.exectuIUD(sql, params);
      }
      /**
       * 
       * @param id
       * @return
       */
      public static int del(String id) {
    	  String sql = "delete from shopuser where USER_ID=? and USER_STATUS=1";
    	 Object[] params = {id};
    	  return Basedao.exectuIUD(sql, params);
      }
      
      
      
      
      
      
      
      
      
      
     /**
      * 获取总记录数和总页数
      * @param count
      * @return
      */
      public static int update(LMONKEY_USER u) {
    	  String sql = "update shopuser set USER_NAME=?, USER_PASSWORD=?, USER_SEX=?, USER_BIRTHDAY=?, USER_IDENITY_CODE=?, USER_EMAIL=?, USER_MOBILE=?, USER_ADDRESS=?,USER_STATUS=? where USER_ID = ?"; 
    	  Object[] params= {
    			  u.getUSER_NAME(),
    			  u.getUSER_PASSWORD(),
    			  u.getUSER_SEX(),
    			  u.getUSER_BIRTHDAY(),
    			  u.getUSER_IDENITY_CODE(),
    			  u.getUSER_EMAIL(),
    			  u.getUSER_MOBILE(),
    			  u.getUSER_ADDRESS(),
    			  u.getUSER_STATUS(),
    			  u.getUSER_ID()
    			  
    	  };
		return Basedao.exectuIUD(sql, params);
      }
      
      
      public static int selectByName(String id) {
          int count = 0;
    	  Connection conn = Basedao.getconn();
    	  PreparedStatement ps = null;
    	  ResultSet rs = null;
    	  
    	  try {
    		String sql = "select count(*) from shopuser where USER_ID=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    	
    		rs = ps.executeQuery();
           while(rs.next()) {
        	   count = rs.getInt(1);
           }
			
			
		
    	  }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
    	  return count;
      }
      
      
      
      
      
      
      
      
      
      
      
      public static int[] totalPage(int count, String keyword) {
    	  int arr[] = {0,1};
    	  Connection conn = Basedao.getconn();
    	  PreparedStatement ps = null;
    	  ResultSet rs = null;
    	  
    	  try {
    		String sql = "select count(*) from shopuser where USER_ID like '%"+keyword+"%'";
    		ps = conn.prepareStatement(sql);
    		if(keyword!=null) {
    			
    		}else {
    			sql = "select count(*) from shopuser";
    			ps = conn.prepareStatement(sql);
    		}
    		
			
			
			rs = ps.executeQuery();
			while(rs.next()) {
				arr[0]=rs.getInt(1);
				
				if(arr[0]%count==0) {
					arr[1] = arr[0]/count;
					}else {
						arr[1] = arr[0]/count+1;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
    	  return arr;
      }
      
      
      
      public static ArrayList<LMONKEY_USER> selectAll(String keyword) {//int cpage, int count
    	  ArrayList<LMONKEY_USER> list=new ArrayList<LMONKEY_USER>();
    	  ResultSet rs = null;//声明结果集
    	  //获取连接对象
    	  Connection conn = Basedao.getconn();
    	   PreparedStatement ps = null;
    	   
    	   try {
        String sql = "";
        if(keyword!=null) {
        	sql="select * from shopuser where USER_ID like '%"+keyword+"%'  order by USER_BIRTHDAY";//'%"+keyword+"%'
        }else {
    		   
    	 sql = "select * from shopuser order by USER_BIRTHDAY";
        } 
    	 //String sql = "Select Top 3  * From shopuser Where USER_ID in  Select Top 6 USER_ID From shopuser Order by USER_ID Order by USER_ID Desc";
			ps = conn.prepareStatement(sql);
			//ps.setString(1, "%"+keyword+"%");
			// ps.setInt(1, (cpage-1)*count);
			 
			// ps.setInt(2, count);
			rs=ps.executeQuery();
			
			
			while(rs.next()) {
				LMONKEY_USER u = new LMONKEY_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_IDENITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
						);
				list.add(u);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
    	  return list;
      }
      
      
      
      
      /**
       * 通过id查找用户
       * @param id
       * @return
       */
      
      public static LMONKEY_USER selectByID(String id) {
    	 LMONKEY_USER u = null;
    	 
    	  ResultSet rs = null;//声明结果集
    	  //获取连接对象
    	  Connection conn = Basedao.getconn();
    	  
    	   PreparedStatement ps = null;
    	   
    	   try {
        String sql = "select * from shopuser where USER_ID=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        
        
        
		rs=ps.executeQuery();
			
			
			while(rs.next()) {
				 u = new LMONKEY_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_IDENITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
						);
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
    	  return u;
      }
      
      
      
      public static int selectByNM(String name, String pwd) {
    	  int count = 0;
    	  
    	  Connection conn = Basedao.getconn();
    	  PreparedStatement ps = null;
    	  ResultSet rs = null;
    	  
    	  
    	  
    	  try {
    		String sql = "select count(*) from shopuser where USER_ID=? and USER_PASSWORD=?";
    		ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, name);
    		ps.setString(2, pwd);
    	
    		rs = ps.executeQuery();
    		
           while(rs.next()) {
        	   count = rs.getInt(1);
           }
			
			
		
    	  }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
    	  return count;
      }
      
      /**
       * 通过用户名和密码查询用户信息
       * @param name
       * @param psd
       * @return
       */
      
      
      
	public static LMONKEY_USER selectAdmin(String name, String pwd) {
		
		 LMONKEY_USER u = null;
    	 
   	  ResultSet rs = null;//声明结果集
   	  //获取连接对象
   	  Connection conn = Basedao.getconn();
   	  
   	   PreparedStatement ps = null;
   	   
   	   try {
       String sql = "select * from shopuser where USER_ID=? and USER_PASSWORD=?";
       ps = conn.prepareStatement(sql);
       ps.setString(1, name);
       ps.setString(2, pwd);
       
       
		rs=ps.executeQuery();
			
			
			while(rs.next()) {
				 u = new LMONKEY_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_IDENITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
						);
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
   	  return u;

	}
     
      
      
}
