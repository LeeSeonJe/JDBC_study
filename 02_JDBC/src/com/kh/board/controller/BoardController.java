package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.view.View;

public class BoardController {
	View view = new View();
	BoardService bService = new BoardService();
	ArrayList<Board> bList = null;

	public void selectAll() {
		bList = new ArrayList<Board>();
		bList = bService.selectAll();
		if (!bList.isEmpty()) {
			view.selectAll(bList);
		} else {
			view.displayError("글이 존재하지 않습니다.");
		}
	}

	public void selectOne() {
		int no = view.inputBNo();
		if (bService.selectOne(no) != null) {
			view.selectOne(bService.selectOne(no));
		} else {
			view.displayError("글이 존재하지 않습니다.");
		}
	}

	public void insertBoard() {
		int result = bService.insertBoard(view.insertBoard());
		if (result > 0) {
			view.displaySuccess(result + "개의 행이 추가되었습니다.");
		} else {
			view.displayError("데이터 삽입 과정 중 오류 발생");
		}
	}

	public void updateBoard(String id) {
		int no = view.inputBNo();
		if (bService.existBNo(no) > 0 && bService.checkId(id, no)) {
			String content = view.updateContent();
			int result = bService.updateBoard(no, content);
			if (result > 0) {
				view.displaySuccess(result + "개의 행이 수정되었습니다.");
			} else {
				view.displayError("데이터 수정 과정 중 오류 발생");
			}
		} else if (!bService.checkId(id, no)) {
			view.displayError("아이디가 일치하지 않습니다");
		} else {
			view.displayError("존재하지 않는 글입니다.");
		}
	}

	public void deleteBoard(String id) {
		int no = view.inputBNo();
		if (bService.existBNo(no) > 0 && bService.checkId(id, no)) {
			char yn = view.deleteBoard();
			if (yn == 'y' || yn == 'Y') {
				int result = bService.deletBoard(no, yn);
				if (result > 0) {
					view.displaySuccess(result + "개의 행이 삭제되었습니다.");
				} else {
					view.displayError("데이터 삭제 과정 중 오류 발생");
				}
			} else {
				view.displayError("데이터 삭제 과정 중 오류 발생");
			}
		} else if (!bService.checkId(id, no)) {
			view.displayError("아이디가 일치하지 않습니다");
		} else {
			view.displayError("존재하지 않는 글입니다.");
		}
	}

}
