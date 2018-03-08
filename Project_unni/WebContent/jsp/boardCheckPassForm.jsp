<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<script type="text/javascript" src="script/unni.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PASSWORD CHECK</title>
</head>
<body>
	<div align="center">
		<h2 id ="passCheck">PASSWORD CHECK</h2>
		<form action = "unni" name = "pwCheckfrm" method="post">
			<input type="hidden" name = "command" value = "boardCheckPass">
			<input type="hidden" name = "num" value = "${param.num}">
			<table>
				<tr>
					<th>PASSWORD</th>
					<td><input type="password" name = "password" size="20"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value ="OK" onclick="return passCheck()">
		</form>
		${msg}
	</div>

</body>
</html>