package com.ezen.myproject.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.commentVO;
import com.ezen.myproject.repository.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService
{
	@Inject
	private CommentDAO cdao;

	@Override
	public int post(commentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.insert(cvo);
	}

	@Override
	public List<commentVO> getList(int bno) {
		// TODO Auto-generated method stub
		return cdao.getList(bno);
	}

	@Override
	public int update(commentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.remove(cno);
	}
}
