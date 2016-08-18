<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Form example - Add User Model</title>
	</head>

	<body>
		
		<h1>Add user page - Add User Model</h1>
		
		<br/>
		
		<h3>
			This generates the form using Spring form tags to fill the fields with User model ==> @ModelAttribute("defaultUser"). I.e.: the firstName field will be initialized with the value returned by
		    defaultUser.getFirstName()
			<br/>
			<br/>
			This form goes to addUserModelSubmit method ==> @RequestMapping(value="/addUserModel")
		</h3>
		
		<form:form method="POST" modelAttribute="defaultUser" action="addUserModel">
			<!-- path="firstName" and path="age" match with attributes in moel (User.java and @ModelAttribute("defaultUser") in UserController.java) -->
			<form:input path="firstName" />
			<form:input path="age" />
			<input type="submit" value="Submit" />
		</form:form>
		
		<br/>
		<br/>
		
		<h3>
			This uses a new User object in controller and set values in that object automatically with the form field values
			<br/>
			<br/>
			This form goes to addUserModelAutNewObjectUser method ==> @RequestMapping(value="/addUserModelAutNewObjectUser")
		</h3>
		
		<form:form method="POST" modelAttribute="defaultUser" action="addUserModelAutNewObjectUser">
			<!-- path="firstName" and path="age" match with attributes in moel (User.java and @ModelAttribute("defaultUser") in UserController.java) -->
			<form:input path="firstName" />
			<form:input path="age" />
			<input type="submit" value="Submit" />
		</form:form>
		
		<br/>
		<br/>
		
		<h3>
			This uses the same User object loaded before in controller and set values in that object automatically with the form field values
			<br/>
			<br/>
			This form goes to addUserModelAutSameObjectUser method ==> @RequestMapping(value="/addUserModelAutSameObjectUser")
		</h3>
		
		<form:form method="POST" modelAttribute="defaultUser" action="addUserModelAutSameObjectUser">
			<!-- path="firstName" and path="age" match with attributes in model (User.java and @ModelAttribute("defaultUser") in UserController.java) -->
			<!-- Use one of these Spring form tags -->
			<form:input path="firstName" />
			<form:errors path="firstName" cssclass="error"></form:errors>
			
			<form:input path="age" />
			<form:errors path="age" cssclass="error"></form:errors>
			<!-- Generated HTML code -->
			<!-- 
			<input id="firstName" name="firstName" type="text" value=""/>
			<span id="firstName.errors" cssclass="error">may not be empty</span>
			<input id="age" name="age" type="text" value="1233"/>
			<span id="age.errors" cssclass="error">must be less than or equal to 120</span>
			 -->
			<!-- 
			<form:textarea path="firstName" />
			<form:textarea path="age" />
			<form:password path="firstName" />
			<form:hidden path="firstName" />
			 -->
			 <!-- This is the generated HTML code -->
			 <!-- 
			<input id="firstName" name="firstName" type="text" value="Joe"/>
			<textarea id="firstName" name="firstName">Joe</textarea>
			<input id="firstName" name="firstName" type="password" value=""/>
			<input id="firstName" name="firstName" type="hidden" value="Joe"/>
			 -->
			 
			<!-- Use a form:select element initialized with the @ModelAttribute("countries") Map -->
			<form:select path="country" items="${countries}" />
			
			<!-- Code if we are using List<Country> object -->
			<!-- form:select path="country" items="${countries}" itemValue="code" itemLabel="name" /> -->
			 
			<!-- This is the generated HTML code -->
			<!-- 
			<select id="country" name="country">
				<option value="es">Spain</option>
				<option value="it">Italy</option>
				<option value="de" selected="selected">Germany</option>
				<option value="fr">France</option>
			</select>
			-->
			
			<!-- Checkbox -->
			Married&nbsp;<form:checkbox path="married" />
			<!-- Generated HTML code -->
			<!-- 
			<input id="married1" name="married" type="checkbox" value="true"/>
			<input type="hidden" name="_married" value="on"/>
			 -->
			
			<!-- List of checkboxes -->
			<form:checkboxes path="languages" items="${languages}"  />
			<!-- Generated HTML code -->
			<!-- 
			<span>
				<input id="languages1" name="languages" type="checkbox" value="de"/>
				<label for="languages1">German</label>
			</span>
			<span>
				<input id="languages2" name="languages" type="checkbox" value="en"/>
				<label for="languages2">English</label>
			</span>
			<span>
				<input id="languages3" name="languages" type="checkbox" value="fr"/>
				<label for="languages3">French</label>
			</span>
			<input type="hidden" name="_languages" value="on"/>
			 -->
			 <!-- To have more control on the generated HTML code (to avoid the label and span tags), it's possible
				  to use several form:checkbox elements with the same value for the path attribute: -->
			<!-- 
			<form:checkbox path="languages" value="de" />German
			<form:checkbox path="languages" value="en" />English
			<form:checkbox path="languages" value="fr" />French
			-->
			<!-- The generated HTML code is similar, except for the hidden attribute, which is generated multiple times: -->
			<!-- 
			<input id="languages1" name="languages" type="checkbox" value="de"/>
			<input type="hidden" name="_languages" value="on"/>
			German
			<input id="languages2" name="languages" type="checkbox" value="en"/>
			<input type="hidden" name="_languages" value="on"/>
			English
			<input id="languages3" name="languages" type="checkbox" value="fr"/>
			<input type="hidden" name="_languages" value="on"/>
			French
			-->
			
			<!-- Radiobuttons -->
			<form:radiobuttons path="color" items="${colors}"  /> <!-- 
																      path="color" refers to User attribute "color"
																	  items="${colors}" refers to @ModelAttribute("colors") in UserController
													 			  -->
			
			<!-- Submit button -->
			<input type="submit" value="Submit" />
			
		</form:form>
		
		<br/>
		<br/>
		
		<h3>
			This form goes to addUserUpload method ==> value="addUserUpload")
		</h3>
		
		<!-- Uploading files -->
		<!-- Setting the HTML form encoding to multipart/form-data -->
		<form:form method="POST" modelAttribute="defaultUser" action="addUserUpload" enctype="multipart/form-data">
			<!-- Add a file upload widget -->
			<input type="file" name="file" />
			<!-- 
			To upload several files, you can have several form fields (file1, file2 and so on) and their
		    corresponding arguments (formFile1, formFile2 and so on). It's also possible to use the same field
			name for multiple file upload widgets (this is convenient to allow the user to upload an undetermined
			number of files):
			<input type="file" name="file" />
			<input type="file" name="file" />
			<input type="file" name="file" />
			In this case, we'll retrieve the files as an array of MultipartFile in the controller method:
			@RequestMapping(value="addUser", method=RequestMethod.POST)
			public void addUser(User user, @RequestParam("file") MultipartFile[] form FileArray) {...}
			-->
			
			<!-- Submit button -->
			<input type="submit" value="Submit" />
		</form:form>
	</body>
</html>