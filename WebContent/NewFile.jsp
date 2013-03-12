<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" id="uploader" value="Upload">
	<input type="button" id="uploader01" value="Upload" onclick="do_click()">

	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/upclick/upclick-min.js"></script>
	<script type="text/javascript">
	    function do_click(){
	    	document.getElementById('uploader').click();
	    }
		var uploader = document.getElementById('uploader');
		upclick(
			     {
			      element: uploader,
			      action: 'json/JBPM_Prop.action?action=AttaUpload',
	              action_params: {'f04id':'5'},
				  dataname: 'fileData',
			      onstart:
			        function(filename)
			        {
			    	    // var array_fragment = filename.split('\\');
			    	    // alert($(array_fragment).last()[0]);
			    	    				        
			          alert('Start upload: '+filename);
			        },
			      oncomplete:
			        function(response_data) 
			        {
			          alert(response_data);
			        }
			     });
	</script>	     
</body>
</html>