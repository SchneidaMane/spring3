package main.java.core.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import main.java.vm.google.GoogleMachineManager;

@Controller
@RequestMapping("/")
public class DefaultController implements HandlerExceptionResolver {

	@RequestMapping("/*")
	public String defaultHandler(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		//String trimmedPath = servletPath.replaceAll("/dashboard/", "");
		//Homepage: serve index
		if (servletPath.equals("/")) {
			servletPath = "/index.html";
		}
		String templatePath = "/pages" + servletPath;
		String fullPath = "src/main/resources/templates" + templatePath;
		System.out.println(templatePath);
		File f = new File(fullPath);
		if (f.exists() && !f.isDirectory()) {
			return templatePath;
		}

		return "/error/FileNotFound";
	}

	@GetMapping("/calendar")
	public String calendar() {
		return "/pages/calendar.html";
	}

	//TODO: Sanitize calendarString
	@PostMapping("/calendar")
	public String calendarPost(String calendarString) {
		System.out.println(calendarString);
		GoogleMachineManager manager = new GoogleMachineManager("testaccount@natural-axiom-197904.iam.gserviceaccount.com", "micro1", "us-east1-b");
		manager.selectAccount();
		manager.turnOn();
		//use cookies to get user/machine information.
		System.out.println("handler invoked");
		//should not return until machine schedule has been successfully updated
		return "/pages/calendar.html";
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();

		//TODO: Custom error handling for every error. The file too large error page should only show when ex instanceof MaxUploadSizeExceededException
		System.out.println(ex.getClass());
		if (ex instanceof MaxUploadSizeExceededException) {
			//return new ModelAndView("error/FileUploadTooLargeError", model);
			return new ModelAndView("error/FileUploadTooLargeError", model);
		}
		/*-
		else {
			model.put("uploadedFile", "");
		}
		model.put("uploadedFile", "");
		*/

		// maxSwallowSize is the default of 2MB. This is the amount that you can
		// go over the maximum allowed size before Tomcat will reset the
		// connection
		// If the attempted upload is below the maxFileSize + maxSwallowSize but
		// above the maxFileSize, the following ModelAndView will be used.
		return new ModelAndView("error/FileNotFound", model);
	}
}
