package com.ezen.myproject.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.myproject.domain.memberVO;
import com.ezen.myproject.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController 
{
	@Inject
	private MemberService msv;
	
	@GetMapping("/signup")
	public String signupGet() 
	{
		return "/member/signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(memberVO mvo)
	{
		log.info(">>회원가입 객체>>"+mvo);
		int isOk=msv.signup(mvo);
		log.info((isOk>0)? "OK":"FAIL");
		return "index";
	}
	
	@GetMapping("/login")
	public String loginGet()
	{
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(memberVO mvo,HttpServletRequest request,Model m)
	{
		log.info(">>로그인 사용자>>"+mvo);
		//mvo 객체를 db에서 일치하는지 체크
		memberVO loginmvo=msv.isUser(mvo);
		
		//db에서 가져온 loginmvo가 null이 아니라면 세션에 저장
		if(loginmvo!=null)
		{
			HttpSession ses =request.getSession();
			ses.setAttribute("loginmvo",loginmvo);
			ses.setMaxInactiveInterval(60*10);	//로그인유지시간
		}
		else
		{
			m.addAttribute("msg_login",1);
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logoutGet(HttpServletRequest request,Model m)
	{
		request.getSession().removeAttribute("loginmvo");//세션객체삭제
		request.getSession().invalidate();
		m.addAttribute("msg_logout",1);
		
		return "index";
	}
	@GetMapping("/modify")
	public String modifyGet()
	{
		return "/member/modify";
	}
	
	@PostMapping("/update")
	public String updatePost(memberVO mvo,Model m)
	{
		log.info("modify mvo>>"+mvo);
		int isOk=msv.modify(mvo);
		log.info((isOk>0)? "OK":"FAIL");
		m.addAttribute("msg_modify",1);
		return "redirect:/member/logout";
	}
}
