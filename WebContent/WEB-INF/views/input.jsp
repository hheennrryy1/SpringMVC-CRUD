<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加员工</title>
</head>
<body>
	<form:form method="POST" action="${ pageContext.servletContext.contextPath }/emp" modelAttribute="employee"> <!-- 要跟Model中的一个JAVABEAN的名字一模一样 -->
		<c:if test="${ empty employee.id }">
			LastName:<form:input path="lastName"/> <br />
		</c:if>
		
		<c:if test="${ not empty employee.id }">
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		
		Email:<form:input path="email"/> <br />
		
		<% 
			Map<String, String> genders = new HashMap<String,String>();
			genders.put("1", "Male");
			genders.put("0", "Female");
			request.setAttribute("genders", genders);
		%>
		Gender: 
		<br />
		<form:radiobuttons path="gender" items="${genders}" delimiter="<br>"/>
		<br />
		Department:
		 <form:select path="department.id" 
			items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
			<br />
		<input type="submit" value="提交"/>
	</form:form>
</body>
</html>