package com.ezen.myproject.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ezen.myproject.domain.commentVO;

public interface CommentService 
{

	int post(commentVO cvo);

	List<commentVO> getList(int bno);

	int update(commentVO cvo);

	int remove(int cno);
	
}
