package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	private MemberMenu menu = new MemberMenu();
	private MemberService service = new MemberService();

	public void insertMember() {
		Member member = menu.insertMember();
		int result = service.inserMember(member);

		if (result > 0) {
			menu.displaySuccess(result + "개의 행이 추가되었습니다.");
		} else {
			menu.displayError("데이터 삽입 과정 중 오류 발생");
		}
	}

	public void selectAll() {
		ArrayList<Member> mList = service.selectAll();
		if (!mList.isEmpty()) {
			menu.displayMember(mList);
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	public void selectMember() {
		int input = menu.selectMember();

		ArrayList<Member> mList = null;

		if (input == 1) {
			String id = menu.inputMemberId();
			mList = service.selectMemberId(id);
		} else if (input == 2) {
			char gender = menu.inputGender();
			mList = service.selectGender(gender);
		} else if (input == 0) {
			return;
		}

		if (!mList.isEmpty()) {
			menu.displayMember(mList);
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
	}
}
