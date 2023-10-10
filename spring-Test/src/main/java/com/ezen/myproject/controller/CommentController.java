package com.ezen.myproject.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.myproject.domain.commentVO;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
public class CommentController 
{
	@Inject
	public CommentService csv;
	
	//ResponseEntity 객체 사용
	//@RequestBody : body값 추출
	//value="mapping name",consumes:가져오는 데이터의 형태
	//produces:보내는 데이터의 형식 / 나가는 데이터 타입:MediaType.text
	//json:application/json text:text_plain
	@PostMapping(value="/post",consumes ="application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody commentVO cvo) 
	{
		log.info("cvo>>"+cvo);
		//DB연결
		int isOk=csv.post(cvo);
		//리턴시 response의 통신상태를 같이 리턴
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK):new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
