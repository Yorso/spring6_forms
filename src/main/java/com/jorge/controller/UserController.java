package com.jorge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		return user; // It returns an object with default values
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
	public String addUserModelAutSubmitSameObjectUser(@ModelAttribute("defaultUser") User user) { // Instead of making Spring create a new object, 
																								  // you can provide the name of a default one.
																								  // In this case, Spring will use the object returned by the corresponding @ModelAttribute method of
																								  // the controller class to store the submitted form data
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed first name getting from the User object: " + user.getFirstName());
		System.out.println(this.getClass().getSimpleName() + "." + new Exception().getStackTrace()[0].getMethodName() + ": " + "Changed age getting from the User object: " + user.getAge());
		
		return "home"; // Go to home.jsp
	}
}
