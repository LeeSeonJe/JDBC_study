package com.kh.board.model.dao;

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

import com.kh.board.model.vo.Board;

public class BoardDAO {
	Properties prop = null;

	public BoardDAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Board> selectAll(Connection conn, ArrayList<Board> bList) {
		Statement stmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAll");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			Board b = null;
			while (rset.next()) {
				if (rset.getString("DELETE_YN").charAt(0) == 'N') {
					int bNo = rset.getInt("BNO");
					String title = rset.getString("TITLE");
					String content = rset.getString("CONTENT");
					Date createDate = rset.getDate("CREATE_DATE");
					String writer = rset.getString("WRITER");
					b = new Board(bNo, title, content, createDate, writer);
					bList.add(b);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return bList;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Board selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String query = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rset = pstmt.executeQuery();

			if (rset.next() && rset.getString("DELETE_YN").charAt(0) == 'N') {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
				String content = rset.getString("CONTENT");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				b = new Board(bNo, title, content, createDate, writer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public int existBNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("existBNo");
		int bNo = 0;
		Board b = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if (rset.next() && rset.getString("DELETE_YN").charAt(0) == 'N') {
				bNo = rset.getInt("BNO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bNo;
	}

	public int updateBoard(Connection conn, int no, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean checkId(Connection conn, String id, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkId");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			rset = pstmt.executeQuery();
			if (rset.next() && rset.getString("WRITER").equals(id)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return false;
	}

	public int deletBoard(Connection conn, int no, char yn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deletBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, String.valueOf(yn));
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
