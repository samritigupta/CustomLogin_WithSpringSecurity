package com.springmvclearn.security.customloginform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showLanding() {
		return "landing";
	}

	//@GetMapping("/")
	@GetMapping("/employees")
	public String showHome() {
		return "home";
	}
}
