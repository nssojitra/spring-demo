package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("")
	public String Index()
	{
		return "<h1>Welcome to REST API</h1>";
	}

}
