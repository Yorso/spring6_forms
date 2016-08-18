package com.jorge.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jorge.model.User;

@Controller // This says this class is a controller
public class UserController {

	// Controller method to display the form.
	// It's called for HTTP GET requests
	@RequestMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	
	// Controller method to process the form when it's submitted.
	// It's called for HTTP POST requests (because of method=RequestMethod.POST )
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String addUserSubmit(HttpServletRequest request) {
		System.out.println(new Exception().getStackTrace()[0].getMethodName()); // Name of current method to console
		String firstName = request.getParameter("firstName");

		//return "redirect:/home"; // Doesn't work
		return "home"; // Go to home.jsp
	}
	
	// Specifying "action" in addUser.jsp form
	@RequestMapping(value="/addUserAgain", method=RequestMethod.POST)
	public String addUserSubmitAgain(HttpServletRequest request) {
		System.out.println(new Exception().getStackTrace()[0].getMethodName()); // Name of current method to console
		String firstName = request.getParameter("firstNameAgain");

		//return "redirect:/home"; // Doesn't work
		return "home"; // Go to home.jsp
	}
	
	/**
	 * Getting a submitted form value using a controller method argument
	 * 
	 * The userName argument is initialized by Spring with the submitted value of the name form field.
	 * 
	 * @RequestParam can also retrieve URL parameters, for example, http://localhost:8080/spring6_forms/processForm?name=Merlin
	 * 
	 * Method: GET
	 */
	@RequestMapping("processForm") // Without a return to specify a jsp forwarding (i.e.: return "home";), it tries to find processForm.jsp
	public void processForm(@RequestParam("name") String userName) {
		System.out.println("Name in processForm with @RequestParam: " + userName); // i.e.: name=Merlin
	}
	
	// We can use HttpServletRequest instead of @RequestParam to get URL parameter 'name'
	/*@RequestMapping("processForm") // Without a return to specify a jsp forwarding (i.e.: return "home";), it tries to find processForm.jsp
	public void processForm(HttpServletRequest request) {
		String name = request.getParameter("name"); // 'name' is passed as a parameter in the URL => http://localhost:8080/spring6_forms/processForm?name=Merlin
		System.out.println("Name in processForm with HttpServletRequest: " + name); // i.e.: name=Merlin
	}*/
	
	// Method: POST
	@RequestMapping(value="/processForm", method=RequestMethod.POST) // Without a return to specify a jsp forwarding (i.e.: return "home";), it tries to find processForm.jsp
	public String processFormSubmit(@RequestParam("name") String userName, HttpServletRequest request) {
		System.out.println("Name in @RequestParam: " + userName); // ADD to name got in GET method, the new name got in submit method i.e.: 'name=Merlin, John' because in URL we hace yet '?name=Merlin'
		System.out.println("Name in HttpServletRequest: " + request.getParameter("name")); // We've got only Merlin, even changing name in URL before make submit
		
		return "home"; // Go to home.jsp
	}
	
	/**
	 * Setting a form's default values using a model object
     * 
     * You will learn how to display a form with initial values that the user can change.
	 * 
	 * In the controller, because of @ModelAttribute , the defaultUser() method is automatically called
	 * for each request of the controller. The returned User object is stored in the memory as defaultUser .
	 * In the JSP, defaultUser is used to initialize the form:
	 * 		
	 * 		It's set as modelAttribute of the form:form element:
	 *		<form:form method="POST" modelAttribute="defaultUser">
	 * 		
	 * 		Form fields get their values from the corresponding properties of defaultUser . 
	 * 		For example,the firstName field will be initialized with the value returned by
	 * 		defaultUser.getFirstName() .
	 * 
	 */
	@ModelAttribute("defaultUser") // Settings values to display in form fields when the form loads the first time
	public User defaultUser() {
		User user = new User();
		user.setFirstName("Joe");
		user.setAge(18);
		user.setCountry("de"); // germany is the default country in select field
		user.setMarried(false);
		
		String[] defaultLanguages = {"en", "es"}; // Array because it's a list of checkboxes, you can select several checkboxes
		user.setLanguages(defaultLanguages);
		
		user.setColor("bk"); // Set radiobutton to a default value: black
		
		return user; // It returns an object with default values
	}
	
	@ModelAttribute("countries")
	public Map<String, String> countries() {
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("es", "Spain");
		m.put("it", "Italy");
		m.put("de", "Germany");
		m.put("fr", "France");
		
		return m;
	}
	
	// We can use List<String> instead of Map>String, String>. Jsp code is the same that Map<String, String>
	/*
	@ModelAttribute("countries")
	public List<String> countries() {
		List<String> l = new LinkedList<String>();
		l.add("es");
		l.add("it");
		l.add("de");
		l.add("fr");
		
		return l;
	}
	*/
	
	// Or we can use List<Object> object. For example, Country was a class with code and name String attributes. Jsp code changes
	/*
	@ModelAttribute("countries")
	public List<Country> countries() {
		//...
	}
	*/
	
	// List of checkboxes
	@ModelAttribute("languages")
	public Map<String, String> languages() {
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("en", "English");
		m.put("fr", "French");
		m.put("es", "Spanish");
		m.put("de", "German");
		m.put("it", "Italian");
		
		return m;
	}
	
	// Map of radiobuttons
	@ModelAttribute("colors")
	public Map<String, String> colors() {
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("b", "Blue");
		m.put("bk", "Black");
		m.put("yw", "Yellow");
		m.put("o", "Orange");
		
		return m;
	}
	
	// GET Method to display the form ==> http://localhost:8080/spring6_forms/addUserModel
	@RequestMapping("addUserModel")
	public String addUserModel() {
		return "addUserModel";
	}
	
	// POST Method displaying changed values in form fields after submit
	@RequestMapping(value="/addUserModel" , method=RequestMethod.POST)
	public String addUserModelSubmit(HttpServletRequest request) {
		System.out.println("Changed first name: " + request.getParameter("firstName"));
		System.out.println("Changed age: " + request.getParameter("age"));
		
		return "home"; // Go to home.jsp
	}
	
	/**
	 * Saving form data in an object automatically
	 * 
	 * For forms directly related to a model object, for example, a form to add User , the submitted form
	 * data can be automatically saved in an instance of that object.
	 * 
	 * Use: http://localhost:8080/spring6_forms/addUserModel
	 * 
	 */
	@RequestMapping(value="/addUserModelAutNewObjectUser", method=RequestMethod.POST)
	public String addUserModelAutSubmitNewObjectUser(@ModelAttribute User user) { // Here new User object is created.
													   					          // The form values are injected into the object by matching the form field names 
																				  // to object attribute names. 
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed first name getting from the User object: " + user.getFirstName());
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed age getting from the User object: " + user.getAge());
		
		return "home"; // Go to home.jsp
	}
	
	@RequestMapping(value="/addUserModelAutSameObjectUser", method=RequestMethod.POST)
	public String addUserModelAutSubmitSameObjectUser(@ModelAttribute("defaultUser") @Valid User user, BindingResult result){ // Instead of making Spring create a new object, 
																															  // you can provide the name of a default one.
																															  // In this case, Spring will use the object returned by the corresponding @ModelAttribute method of
																															  // the controller class to store the submitted form data
		
		// With validation form, we check all fields with validation are ok
		if(result.hasErrors()) {// Show the form page again, with the errors
			return "addUserModel";
		}
		else {
			// Validation was successful, redirect to another page
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed first name getting from the User object: " + user.getFirstName());
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed age getting from the User object: " + user.getAge());
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed country getting from the User object: " + user.getCountry());
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed status getting from the User object: " + user.isMarried());
			System.out.print(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed languages getting from the User object: ");
			for(int i = 0; i < user.getLanguages().length; i++){
				System.out.print(user.getLanguages()[i] + " ");
			}
			
			System.out.println("\n" + this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed color getting from the User object: " + user.getColor());
			
			return "home"; // Go to home.jsp
		}
	}
	
	/* 
	// Without validation form
	@RequestMapping(value="/addUserModelAutSameObjectUser", method=RequestMethod.POST)
	public String addUserModelAutSubmitSameObjectUser(@ModelAttribute("defaultUser") User user){
		// Without validation form, we display the result directly without any testing
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed first name getting from the User object: " + user.getFirstName());
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed age getting from the User object: " + user.getAge());
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed country getting from the User object: " + user.getCountry());
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed status getting from the User object: " + user.isMarried());
		System.out.print(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed languages getting from the User object: ");
		for(int i = 0; i < user.getLanguages().length; i++){
			System.out.print(user.getLanguages()[i] + " ");
		}
		
		System.out.println("\n" + this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed color getting from the User object: " + user.getColor());
		
		return "home"; // Go to home.jsp
	}
	*/
	
	/**
	 * Uploading a file
	 * 
	 * To allow a user to upload a file from an HTML form, we need to set the form encoding to
	 * multipart/form-data . On the server side, we will use the fileupload package from the Apache
	 * Commons library to process the uploaded file.
	 * 
	 */
	@Bean
	MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setMaxUploadSize(500000000); // Size max limit (in bytes) for the data to be uploaded
		
		return resolver;
	}
	
	// POST method to handle file uploading, processing the form submission, adding MultipartFile as a @RequestParam argument
	@RequestMapping(value="addUserUpload", method=RequestMethod.POST)
	public String addUserUpload(User user, @RequestParam("file") MultipartFile formFile){
		// Save the uploaded file to a files folder in the Tomcat directory
		try {
			// Create the folder "files" if necessary
			String tomcatFolderPath = System.getProperty("catalina.home");
			File filesFolder = new File(tomcatFolderPath + File.separator + "files");
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Absolute path to upload directory: " + filesFolder.getAbsolutePath());
			
			if (!filesFolder.exists()) {
				filesFolder.mkdirs();
			}
			
			// Write the uploaded file
			File file = new File(filesFolder.getAbsolutePath() + File.separator + formFile.getOriginalFilename());
			System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Absolute path + file name: " + filesFolder.getAbsolutePath() + File.separator + formFile.getOriginalFilename());
			BufferedOutputStream fileStream = new BufferedOutputStream(new
			FileOutputStream(file));
			
			fileStream.write(formFile.getBytes());
			fileStream.close();
			
			System.out.print(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Upload successful");
			return "home"; // Go to home.jsp
		} 
		catch (Exception e) {
			// Deal with the exception...
			System.out.print(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Error while uploading file");
			return "addUserModel";
		}
	}
}
