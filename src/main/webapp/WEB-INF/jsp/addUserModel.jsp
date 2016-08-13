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
			<form:input path="firstName" />
			<form:input path="age" />
			<input type="submit" value="Submit" />
		</form:form>
	</body>
</html>