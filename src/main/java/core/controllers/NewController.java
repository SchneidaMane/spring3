package main.java.core.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/")
public class NewController {
	@RequestMapping("/*")
	public String defaultHandler(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String trimmedPath = servletPath.replaceAll("/dashboard/", "");
		String templatePath = "/pages/" + trimmedPath;
		return templatePath;
	}
}
