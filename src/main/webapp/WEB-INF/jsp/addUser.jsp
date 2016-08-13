<!DOCTYPE HTML>

<html>
	<head>
		<meta charset="utf-8">
		<title>Form example - Add user</title>
	</head>

	<body>
		
		<h1>Add user page</h1>
		
		<br/>
		
		<h3>This form goes to addUserSubmit method ==> @RequestMapping(value="/addUser")</h3>
		<form method="POST">
			<input type="text" name="firstName" />
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