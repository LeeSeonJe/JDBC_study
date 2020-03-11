package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplate.*;
import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;

public class BoardService {

	public ArrayList<Board> selectAll() {
		ArrayList<Board> bList = new ArrayList<Board>();
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		return bDAO.selectAll(conn, bList);
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.insertBoard(conn, b);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public Board selectOne(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		Board b = bDAO.selectOne(conn, no);
		return b;
	}

	public int existBNo(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		return bDAO.existBNo(conn, no);
	}

	public int updateBoard(int no, String content) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.updateBoard(conn, no, content);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public boolean checkId(String id, int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		return bDAO.checkId(conn, id, no);
	}

	public int deletBoard(int no, char yn) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.deletBoard(conn, no, yn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}
