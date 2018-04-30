package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmailVo;

@Controller
public class EmaillistController {
	
	@Autowired
	private EmaillistDao dao;
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String form() {
		System.out.println("form");
		return "WEB-INF/views/form.jsp";
	}
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(@ModelAttribute EmailVo emailVo) {
		System.out.println("add");
		System.out.println(emailVo.toString());
		
		dao.insert(emailVo);
		
		return "redirect:/list"; //redirect식
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<EmailVo> list = dao.getList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/list.jsp"; //forward식
	}
}
