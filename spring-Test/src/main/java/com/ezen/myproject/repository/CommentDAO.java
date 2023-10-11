package com.ezen.myproject.repository;

import java.util.List;

import com.ezen.myproject.domain.commentVO;

public interface CommentDAO {

	int insert(commentVO cvo);

	List<commentVO> getList(int bno);

	int update(commentVO cvo);

	int remove(int cno);

}
