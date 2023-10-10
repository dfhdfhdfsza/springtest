package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.boardVO;
import com.ezen.myproject.domain.pagingVO;

public interface BoardService {

	int register(boardVO bvo);

	List<boardVO> getList(pagingVO pgvo);

	boardVO getDetail(int bno);

	int modify(boardVO bvo);

	int remove(int bno);

	int getTotalCount(pagingVO pgvo);


}
