package org.iaff.csiaff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DashboardController {


	@GetMapping("/")
	public ModelAndView principal() {
		return new ModelAndView("redirect:/estatisticas");
	}

	@GetMapping("/estatisticas")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		return mv;
	}
	
	
	
	
}
