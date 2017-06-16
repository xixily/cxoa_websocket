package com.chaoxing.oa.controller.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chaoxing.oa.service.TestService;

@Controller
@RequestMapping("/public/test")
public class TestController {
	@Autowired
	TestService testService;
	
	@RequestMapping(name="getSomething")
	public void findSomeThing(){
		testService.findSomeThings();
	}
}
