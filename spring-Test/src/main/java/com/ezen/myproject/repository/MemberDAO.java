package com.ezen.myproject.repository;

import com.ezen.myproject.domain.memberVO;

public interface MemberDAO {

	int signup(memberVO mvo);

	memberVO getUser(String id);

	int update(memberVO mvo);

}
