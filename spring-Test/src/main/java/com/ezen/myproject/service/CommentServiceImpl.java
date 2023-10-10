package com.ezen.myproject.service;

import javax.inject.Inject;

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
}
