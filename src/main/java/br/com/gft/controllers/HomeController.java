package br.com.gft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class HomeController {
	

	@GetMapping
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}
