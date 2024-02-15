package com.brainstation23.ecommerce.ecommerce.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("default")
public class HomeController {

	@GetMapping("/Details")
	public String home() {
		return "index";
	}
	@GetMapping("/")
	public String greeting() {
		return "home";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
