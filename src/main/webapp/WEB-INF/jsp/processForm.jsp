<!DOCTYPE HTML>

<html>
	<head>
		<meta charset="utf-8">
		<title>Form example - Process Form</title>
	</head>

	<body>
		
		<h1>Add user page - Process Form</h1>
		
		<br/>
		
		<h3>This form goes to processFormSubmit method ==> @RequestMapping(value="/processForm")</h3>
		<form method="POST">
			<input type="text" name="name" />
			<input type="submit" />
		</form>
		 
		<br/>
		
		<h3>This form goes to addUserSubmitAgain method ==> @RequestMapping(value="/addUserAgain")</h3>
		<form method="POST" action="addUserAgain">
			<input type="text" name="firstNameAgain" />
			<input type="submit" />
		</form>
	</body>
</html>