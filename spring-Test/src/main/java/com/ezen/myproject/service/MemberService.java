package com.ezen.myproject.service;

import com.ezen.myproject.domain.memberVO;

public interface MemberService 
{

	int signup(memberVO mvo);

	memberVO isUser(memberVO mvo);

	int modify(memberVO mvo);

}
