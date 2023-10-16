package com.ezen.myproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.myproject.domain.boardDTO;
import com.ezen.myproject.domain.boardVO;
import com.ezen.myproject.domain.pagingVO;

public interface BoardDAO 
{

	int insert(boardVO bvo);

	List<boardVO> getList(pagingVO pgvo);

	boardVO getDetail(int bno);

	int readcount(@Param("bno")int bno,@Param("cnt")int cnt);

	int modify(boardVO bvo);

	int remove(int bno);

	int totalcount(pagingVO pgvo);

	int selectBno();

	void updateCount();

}
