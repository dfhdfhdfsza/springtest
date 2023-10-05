package com.ezen.myproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.boardVO;
import com.ezen.myproject.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class BoardServiceImpl implements BoardService 
{
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(boardVO bvo) 
	{
		log.info("register check 2");
		
		return bdao.insert(bvo);
	}

	@Override
	public List<boardVO> getList() {
		log.info("List check 2");
		return bdao.getList();
	}

	@Override
	public boardVO getDetail(int bno) {
		//read_count +1
		log.info("detail check 2");
		bdao.readcount(bno,1);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(boardVO bvo) {
		//수정할때 들어가는 부당 read_count 2개
		//read_count -2
		log.info("modify check 2");
		bdao.readcount(bvo.getBno(),-2);
		return bdao.modify(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check 2");
		return bdao.remove(bno);
	}


}
