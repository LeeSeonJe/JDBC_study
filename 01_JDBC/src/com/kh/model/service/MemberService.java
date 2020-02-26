package com.kh.model.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberService {

	public int inserMember(Member member) {
		Connection conn = getConnection();

		MemberDAO mDAO = new MemberDAO();

		int result = mDAO.inserMember(conn, member);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();

		ArrayList<Member> mList = mDAO.selectAll(conn);

		return mList;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();

		ArrayList<Member> mList = mDAO.selectMemberId(conn, id);
		return mList;
	}

	public ArrayList<Member> selectGender(char gender) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();

		ArrayList<Member> mList = mDAO.selectGender(conn, gender);
		return mList;
	}
}
