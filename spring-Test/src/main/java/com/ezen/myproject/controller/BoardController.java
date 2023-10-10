package com.ezen.myproject.controller;

import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.boardVO;
import com.ezen.myproject.domain.pagingVO;
import com.ezen.myproject.handler.PagingHandler;
import com.ezen.myproject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController 
{
	@Inject
	private BoardService bsv;
	
	@GetMapping("/register")
	public String boardRegisterGet() 
	{
		//servlet-context.xml에서 자동으로 .jsp를 붙여주는 설정을 해줘서 별도로 안붙여줘도됨
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(boardVO bvo)
	{
		log.info(">>>"+bvo.toString());
		int isOk=bsv.register(bvo);
		log.info("board register>>"+(isOk>0? "OK":"FAIL"));
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model,pagingVO pgvo) 
	{
		log.info(">>pgvo>>"+pgvo);
		//getList(pgvo); 수정
		List<boardVO>list=bsv.getList(pgvo);
		int totalCount=bsv.getTotalCount(pgvo); //등록
		log.info("totalcount>"+totalCount);
		PagingHandler ph= new PagingHandler(pgvo, totalCount);
		log.info("ph>"+ph);
		model.addAttribute("list", list);
		model.addAttribute("ph",ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model model,@RequestParam("bno")int bno) 
	{
		log.info("detail bno>>"+bno);
		boardVO bvo =bsv.getDetail(bno);
		model.addAttribute("bvo", bvo);
	}
	
	//수정할때 들어가는 부당 read_count 2개
	@PostMapping("/modify")
	public String modify(boardVO bvo,RedirectAttributes reAttr)
	{
		int isOk=bsv.modify(bvo);
		log.info((isOk>0)? "OK":"FAIL");
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno,RedirectAttributes reAttr) 
	{
		log.info("remove check 1");
		int isOk=bsv.remove(bno);
		log.info((isOk>0)? "OK":"FAIL");
		reAttr.addFlashAttribute("isOk",isOk);
		return "redirect:/board/list";
	}
	
}
