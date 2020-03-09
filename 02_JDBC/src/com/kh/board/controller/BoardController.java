package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.view.View;

public class BoardController {
	View view = new View();
	BoardService bService = new BoardService();

	public void selectAll() {
		ArrayList<Board> bList = new ArrayList<Board>();
		view.selectAll(bService.selectAll(bList));
	}

	public void selectOne() {
		// TODO Auto-generated method stub

	}

	public void insertBoard() {
		bService.insertBoard(view.insertBoard());
	}

	public void updateBoard() {
		// TODO Auto-generated method stub

	}

	public void deleteBoard() {
		// TODO Auto-generated method stub

	}

}
