package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		MemberController mc = new MemberController();

		while (true) {
			System.out.println("*** 회원 관리 프로그램 ***");
			System.out.println("1. 새 회원 등록");
			System.out.println("2. 모든 회원 조회");
			System.out.println("3. 특정 조건 회원 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("$ Menu > ");
			int input = sc.nextInt();
			if (input == 1) {
				mc.insertMember();
			} else if (input == 2) {
				mc.selectAll();
			} else if (input == 3) {
				mc.selectMember();
			} else if (input == 4) {
				mc.updateMember();
			} else if (input == 5) {
				mc.deleteMember();
			} else if (input == 0) {
				System.out.println("프로그램 종료");
				break;
			} else {
				System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
			}
		}
	}

	public Member insertMember() {
		System.out.print("회원 아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		System.out.print("성별(남:M, 여:F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		System.out.print("전화번호(-포함) : ");
		String phone = sc.nextLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.print("주소 : ");
		String address = sc.nextLine();

		Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address);

		return member;
	}

	public void displaySuccess(String string) {
		System.out.println("서비스 요청 성공 : " + string);
	}

	public void displayError(String string) {
		System.out.println("서비스 요청 실패 : " + string);
	}

	public void displayMember(ArrayList<Member> mList) {
		System.out.printf("%-10s %-10s %-5s %-10s %-20s %-15s %-4s %-20s %-15s\n", "ID", "PWD", "NAME", "GENDER",
				"EMAIL", "PHONE", "AGE", "ADDRESS", "ENROLLDATE");

		for (Member m : mList) {
			System.out.printf("%-10s %-10s %-5s %-10s %-20s %-15s %-4s %-20s %-15s\n", m.getMemberId(),
					m.getMemberPwd(), m.getMemberName(), m.getGender(), m.getEmail(), m.getPhone(), m.getAge(),
					m.getAddress(), m.getEnrollDate());
		}

//		for (int i = 0; i < mList.size(); i++) {
//			System.out.println(mList.get(i));
//		}
		// Member class에서 작성한 toString을 사용하는 for문
	}

	public int selectMember() {
		int input = 0;

		while (true) {
			System.out.println("1. 아이디로 회원 조회");
			System.out.println("2. 성별로 회원 조회");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("$ Menu > ");
			input = Integer.parseInt(sc.nextLine());

			if (input == 1) {
				return input;
			} else if (input == 2) {
				return input;
			} else if (input == 0) {
				return input;
			} else {
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}

	public String inputMemberId() {
		System.out.print("회원 아이디 : ");
		String id = sc.nextLine();
		return id;
	}

	public char inputGender() {
		System.out.print("조회할 성별 입력(남:M / 여:F) : ");
		char gen = sc.nextLine().toUpperCase().charAt(0);
		return gen;
	}

	public String checkMemberId() {
		System.out.print("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		return id;
	}

	public String checkMemberPwd() {
		System.out.print("비밀번호 입력를 입력하세요 : ");
		String pwd = sc.nextLine();
		return pwd;
	}

	public Member updateMember() {
		System.out.println("*** 회원 정보 수정 ***");
		System.out.print("회원 아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		System.out.print("성별(남:M, 여:F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		System.out.print("전화번호(-포함) : ");
		String phone = sc.nextLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.print("주소 : ");
		String address = sc.nextLine();

		Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address);
		return member;
	}
}
