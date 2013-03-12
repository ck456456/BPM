<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	RemoteUser: <%= request.getRemoteUser() %><br>
	使用者: ${ G_F02.bpm02001 }(${ G_F02.bpm02002 }) ; <br>
    <script type="text/javascript">
    	kc_f01({' 123 ':'software.sopili.net',' 456 ':'dev.sopili.net'});
    	function kc_f01(url_arr) {
        	for(var key in url_arr){
            	document.write(key+' => '+url_arr[key]+' <br>');
            }
    	}
    </script>
</body>
</html>