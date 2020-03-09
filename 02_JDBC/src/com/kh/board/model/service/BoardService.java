package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplate.*;
import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;

public class BoardService {

	public ArrayList<Board> selectAll(ArrayList<Board> bList) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		return bDAO.selectAll(conn, bList);
	}

	public void insertBoard(Board b) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		bDAO.insertBoard(conn, b);
	}

}
