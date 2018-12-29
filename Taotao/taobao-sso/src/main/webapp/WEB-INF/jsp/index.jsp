<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
</head>
<body>
	<form method="post" action="/user/getuser">
		用户名：<input type="text" name="username"/></br>
		密码：<input type="password" name="password"/></br>
		手机：<input type="text" name="phone"/></br>
		邮箱：<input type="text" name="email"/></br>
		创建日期：<input type="datetime" name="created"/></br>
		修改日期：<input type="datetime" name="updated"/></br>
		<input type="submit" />
	</form>
</body>
</html>