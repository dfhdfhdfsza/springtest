package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.boardVO;

public interface BoardService {

	int register(boardVO bvo);

	List<boardVO> getList();

	boardVO getDetail(int bno);

	int modify(boardVO bvo);

	int remove(int bno);


}
