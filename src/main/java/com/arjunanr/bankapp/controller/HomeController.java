package com.arjunanr.bankapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/healthcheck")
	public String healthCheck() {
		return "Service Running";
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
}
