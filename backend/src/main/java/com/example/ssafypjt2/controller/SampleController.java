package com.example.ssafypjt2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

	// 안녕? ~ ㅎ 
	@GetMapping("/error")
	public String errorpage() {
		throw new IllegalStateException("Error");
	}
}