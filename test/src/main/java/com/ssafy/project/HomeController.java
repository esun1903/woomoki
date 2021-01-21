package com.ssafy.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//이게됨?
@Controller
public class HomeController {

	@GetMapping(value = "/")
	private String home() {
		return "index.html";
	}
}
