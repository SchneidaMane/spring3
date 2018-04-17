package main.java.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/")
public class NewController {
	@RequestMapping("/")
	public String home() {
		return "/pages/index.html";
	}
}
