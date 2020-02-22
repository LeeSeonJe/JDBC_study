package com.kh.view;

import java.util.Scanner;

import com.kh.controller.EmployeeController;

public class Menu {
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		EmployeeController ec = new EmployeeController();

		while (true) {
			System.out.println("1. 전체 사원 정보 조회");
			System.out.println("2. 사번으로 사원 정보 조회");
			System.out.println("3. 새로운 사원 정보 추가");
			System.out.println("4. 사번으로 사원 정보 수정");
			System.out.println("5. 사번으로 사원 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("$ Menu > ");
			int input = sc.nextInt();
			if (input == 1) {

			} else if (input == 2) {

			} else if (input == 3) {

			} else if (input == 4) {

			} else if (input == 5) {

			} else if (input == 0) {
				System.out.println("프로그램 종료");
				break;
			} else {
				System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
			}
		}
	}
}
