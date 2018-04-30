package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.EmailVo;

import oracle.jdbc.pool.OracleDataSource;

@Repository
public class EmaillistDaoOld {

	@Autowired
	private OracleDataSource oracleDataSource;

	public void insert(EmailVo vo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			/*//1.JDBC 드라이버(oracle) 로딩.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"webdb","webdb");*/
			
			conn = oracleDataSource.getConnection();
			
			String query = "insert into emaillist VALUES(SEQ_EMAILLIST_NO.NEXTVAL,?,?,?) ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,vo.getLastName());
			pstmt.setString(2,vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
		
			count = pstmt.executeUpdate();
			
			System.out.println(count+"건 등록");
		} catch (SQLException e) {
			System.out.println("error:  " + e);
		}finally {
			//5.자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				System.out.println("error:  " + e);
			}
		}
	}
	
	public List<EmailVo> getList(){
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<EmailVo> list = new ArrayList<EmailVo>();
		try {
		   /* // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");*/
			
			conn = oracleDataSource.getConnection();
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "select no, last_name,first_name,email "
		    						+"from emaillist "
		    						+"order by no desc ";
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
		    // 4.결과처리
		    while(rs.next()) {
		    		int no = rs.getInt("no");
		    		String lastName = rs.getString("last_name");
		    		String firstName = rs.getString("first_name");
		    		String email = rs.getString("email");
		    		
		    		EmailVo vo = new EmailVo(no,lastName,firstName,email);
		    		list.add(vo);
		    }

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		return list;
	}
	
}
