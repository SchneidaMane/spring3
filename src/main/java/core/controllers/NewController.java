package main.java.core.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/")
public class NewController {

	//Only serves pages in the /pages directory in templates
	@RequestMapping("/*")
	public String defaultHandler(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String trimmedPath = servletPath.replaceAll("/dashboard/", "");
		String templatePath = "/pages/" + trimmedPath;

		String fullPath = "src/main/resources/templates" + templatePath;
		File f = new File(fullPath);
		if (f.exists() && !f.isDirectory()) {
			return templatePath;
		}

		return "/error/FileNotFound";
	}
}
