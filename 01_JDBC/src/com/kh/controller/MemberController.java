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

	public void deleteMember() {
		String id = menu.checkMemberId();
		String pwd = menu.checkMemberPwd();
		boolean idBool = service.checkMemberId(id);
		boolean pwdBool = service.checkMemberPwd(id, pwd);
		if (idBool && pwdBool) { // 아이디 존재 및 비밀번호 일치
			int result = service.deleteMember(id, pwd);
			menu.displaySuccess(result + "개의 데이터가 삭제되었습니다.");
		} else if (!idBool) {
			menu.displayError("아이디가 존재하지 않습니다.");
		} else {
			menu.displayError("비밀번호가 일지하지 않습니다.");
		}
	}

	public void updateMember() {
		String id = menu.checkMemberId();
		String pwd = menu.checkMemberPwd();
		boolean idBool = service.checkMemberId(id);
		boolean pwdBool = service.checkMemberPwd(id, pwd);
		if (idBool && pwdBool) {
			Member member = menu.updateMember();
			int result = service.updateMember(id, member);
			System.out.println(result + "개의 데이터가 수정되었습니다.");
		} else if (!idBool) {
			menu.displayError("아이디가 존재하지 않습니다.");
		} else {
			menu.displayError("비밀번호가 일지하지 않습니다.");
		}
	}
}
