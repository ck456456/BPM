<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>  
   body {overflow: hidden;}   
</style>  
<title>Insert title here</title>
</head>
<body>
	RemoteUser: <%= request.getRemoteUser() %><br>
	<a href="Default.action?formID=Manager.action?action=BPMF02v02" style="font-style:italic;font-weight:bold;">進入表單系統1</a><br>
	<a href="Default.action?formID=WorkFlow.action?action=main%26f04_id=5%26status=approve" style="font-style:italic;font-weight:bold;">進入表單系統2</a><br>
	<a href='Default.action?formID=WorkFlow.action?action=main%26f04_id=6%26status=approve'>進入簽核</a>
	<a href="Default.action" style="font-style:italic;font-weight:bold;">進入表單系統3</a><br><br><br>
	<a href="err01.jsp">按一下</a><br>
	<a href="javascript: hh();">按一下</a><br/>
	
	<a href="Default.action?formID=Manager.action?action=BPMF02v02" style="font-style:italic;font-weight:bold;">上傳測試</a><br>
	
    <script type="text/javascript">
    	function hh(){
        	// alert('--ii--');
        	strss = 'iljhklj';
        	window.open ('Error.action?q=' + strss);
        	/*
        	var url = window.location.pathname;// + '?q=' + strss;
        	alert(url);//to inspect the url string.
        	url01 = "Default.action";
        	xmlhttp=new XMLHttpRequest();
        	xmlhttp.open("GET", url01, true);
        	xmlhttp.send();
        	*/        	
        	}
    </script>
</body>
</html>