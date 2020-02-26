package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int inserMember(Connection conn, Member member) {
//	 	00_JDBC 프로젝트에서 DAT가 했던 업무
//	 		1) JDBC 드라이버 등록
//	 		2) DB 연결 (Connection 객체 생성)
//	 		3) SQL 실행
//	 		4) 트랜잭션 실행
//	 		5) 자원반납

//		실제로 DAO가 처리해야하는 업무 : 3번 (SQL문을 DB로 전달하여 실행하고 반환 값 받아오기)
//		--> 1, 2, 4, 5번 업무 분담 : com.kh.common.JDBCTemplate을 만들어서 분담 시킬 것이다.
		String query = prop.getProperty("insertMember");
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender() + "");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ArrayList<Member> mList = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			mList = new ArrayList<Member>();
			Member member = null;

			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address, enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return mList;
	}

	public ArrayList<Member> selectMemberId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;

		String query = prop.getProperty("selectMemberId");

		try {
			stmt = conn.createStatement();
			query += " '%" + id + "%'";
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			
			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address,
						enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return mList;
	}

	public ArrayList<Member> selectGender(Connection conn, char gender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;
		String query = prop.getProperty("selectGender");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender + "");
			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");

				Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address,
						enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

}
