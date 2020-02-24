package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.EmployeeDAO;
import com.kh.model.vo.Employee;
import com.kh.view.Menu;

public class EmployeeController {
	// View에서 전달 받은 데이터를 가공처리(데이터 변환, 디코딩 등) 하여 DAO로 전달
	// DAO로 부터 전달 받은 결과에 따라 View(출력화면)를 결정하여 데이터 전송

	private EmployeeDAO empDAO = new EmployeeDAO();
	private Menu menu = new Menu();

	public void selectAll() {
		ArrayList<Employee> empList = empDAO.selectAll();

		if (!empList.isEmpty()) { // 조회 결과가 있을 경우
			menu.selelctAll(empList);
		} else { // 조회 결과가 없을 경우
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	public void selectEmployee() {
		int empNo = menu.selectEmpNo(); // 사번 입력 view 호출
		Employee emp = empDAO.selectEmployee(empNo);

		if (emp != null) {
			menu.selectEmployee(emp);
		} else {
			menu.displayError("해당 사번의 검색 결과가 없습니다.");
		}
	}

	public void insertEmployee() {
		Employee emp = menu.insertEmployee();
		int result = empDAO.insertEmployee(emp);
		if (result > 0) {
			menu.displaySuccess(result + "개의 행이 추가 되었습니다.");
		} else {
			menu.displayError("데이터 삽입과정 중 오류 발생");
		}
	}

	public void updateEmployee() {
		int empNo = menu.selectEmpNo();

		Employee emp = menu.updateEmployee();
		emp.setEmpNo(empNo);

		int result = empDAO.updateEmployee(emp);
		if (result > 0) {
			menu.displaySuccess(result + "개의 행이 수정되었습니다.");
		} else {
			menu.displayError("데이터 수정 과정 중 오류 발생");
		}
	}

	public void deleteEmployee() {
		int empNo = menu.selectEmpNo();
		char check = menu.deleteEmployee();
		if (check == 'y') {
			int result = empDAO.deleteEmployee(empNo);

			if (result > 0) {
				menu.displaySuccess(result + "개의 행이 삭제 되었습니다.");
			} else {
				menu.displayError("데이터 삭제 과정 중 오류 발생");
			}
		} else if (check == 'n') {
			menu.displaySuccess("사원 정보 삭제 취소");
		} else {
			menu.displayError("잘못입력하셨습니다. (y 또는 n 입력)");
		}
	}
}