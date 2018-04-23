package main.java.core.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.java.core.persistence.common.MachineScheduleRepository;
import main.java.core.persistence.google.GoogleRepository;
import main.java.fileupload.StorageService;
import main.java.vm.google.JsonActivator;

@Controller
//This is the String that the user puts in the address bar.
//For example, if the Request mapping is /templates/, localhost:8080/templates/ will come here
@RequestMapping("/templates/")
public class MachineScheduleController implements HandlerExceptionResolver, ApplicationContextAware {

	private MachineScheduleRepository machineScheduleRepo;
	private GoogleRepository googleRepository;
	private DispatcherServlet dispatcherServlet;
	private final StorageService storageService;
	private ApplicationContext ctx;

	@Autowired
	public MachineScheduleController(MachineScheduleRepository machineScheduleRepo, GoogleRepository googleRepository, DispatcherServlet dispatcherServlet, StorageService storageService) {
		this.machineScheduleRepo = machineScheduleRepo;
		this.googleRepository = googleRepository;
		this.dispatcherServlet = dispatcherServlet;
		this.storageService = storageService;
	}

	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		String[] beans = ctx.getBeanDefinitionNames();
		for (String string : beans) {
			System.out.println(string);
		}

		model.addAttribute("files", storageService.loadAll().map(path -> MvcUriComponentsBuilder.fromMethodName(MachineScheduleController.class, "serveFile", path.getFileName().toString()).build().toString()).collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		String fileName = file.getOriginalFilename();

		JsonActivator jsonActivator = new JsonActivator(fileName, googleRepository);
		boolean success = false;
		success = jsonActivator.activateServiceAccount();
		if (success) {
			redirectAttributes.addFlashAttribute("message", "Service account was added successfully!");
			return "redirect:/";
		}
		redirectAttributes.addFlashAttribute("message", "Service account was not added successfully. Please try again.");
		return "redirect:/";
	}

	/*-
	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "uploadFormery";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload2() {
		return "hello";
	}
	*/
	// This is the one selected by the resolveException
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

	public void setApplicationContext(ApplicationContext context) {
		this.ctx = context;
	}

	/*-
		// @GetMapping("/")
		@RequestMapping(method = RequestMethod.GET)
		// @ResponseBody
		public String home(Map<String, Object> model) {
			machineScheduleRepo.selectDatabase("awesomedatabase");
			@SuppressWarnings("unused")
			List<MachineSchedule> machineSchedules = machineScheduleRepo.findAll();
			model.put("machineSchedules", machineSchedules);
			// return "home";
			// return "index.html";
			// return "index.html";
			// return new ModelAndView("index");
			return "index";
		}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(MachineSchedule machineSchedule) {
		machineScheduleRepo.save(machineSchedule);
		return "redirect:/";
	}
	
	*/
}
