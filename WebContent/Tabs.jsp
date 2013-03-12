<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="css/NewFile.css"/>
<title>Insert title here</title>
</head>
<body class="ui-form">
	<input type="button" id="uploader" value="Upload">
	<h2 class="demoHeaders">Tabs</h2>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">First</a></li>
			<li><a href="#tabs-2">Second</a></li>
			<li><a href="#tabs-3">Third</a></li>
		</ul>
		<div id="tabs-1">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget diam nec urna hendrerit tempus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum aliquam ligula non nulla cursus volutpat. Aliquam malesuada felis nec turpis auctor interdum. Cras et lobortis dolor. Nam sodales, dolor eu cursus faucibus, justo leo vestibulum turpis, id malesuada erat ipsum et leo. Integer id aliquam augue. Proin quis risus magna. Vivamus eget accumsan mauris. Vivamus justo ligula, blandit et consequat nec, vulputate a elit. Morbi feugiat metus in ante scelerisque a pulvinar quam condimentum. Suspendisse potenti. Pellentesque convallis scelerisque nunc eget dapibus. Pellentesque ut enim justo. Fusce egestas urna sed nulla placerat iaculis. Cras dignissim tincidunt molestie. Suspendisse rutrum dignissim tortor quis mollis. Sed tempor feugiat ligula in imperdiet. Quisque molestie viverra erat in adipiscing. </div>
		<div id="tabs-2">Donec porta malesuada massa, at fermentum nunc tincidunt ac. Integer nisi ligula, sodales bibendum interdum id, sodales sit amet orci. In rhoncus libero in purus vestibulum consequat. Aenean vel purus molestie leo pharetra sagittis. In hac habitasse platea dictumst. Quisque bibendum, metus a egestas convallis, est orci dapibus odio, vel pretium ante erat quis ipsum. Praesent at est in odio rhoncus cursus in sollicitudin velit. Aenean auctor molestie hendrerit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec volutpat nisl eget nisi consequat pretium. Nullam dui purus, commodo a vestibulum nec, semper varius nulla. </div>
		<div id="tabs-3">Mauris tempor urna non quam tincidunt aliquam. Proin venenatis metus eu purus interdum a vehicula ipsum sollicitudin. Mauris sed dignissim odio. Nam id odio sed diam dapibus dignissim. Nulla vestibulum nisl ac magna scelerisque adipiscing. Nam pellentesque sapien vulputate nisi scelerisque ut vehicula orci luctus. Sed rhoncus tempor metus vitae egestas. Proin pharetra tristique justo ac rutrum. Vestibulum ac pharetra dolor. Cras nibh arcu, bibendum eget luctus id, rhoncus nec augue. Integer ornare rhoncus nulla, eu aliquam mi ornare ullamcorper. Praesent egestas auctor orci, non rhoncus sapien eleifend at. Quisque eget purus sem, ut dignissim ligula. Etiam sollicitudin dui libero, id ullamcorper metus. </div>
	</div>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.8.13/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$('#tabs').tabs();
		var uploader = document.getElementById('uploader');
		upclick(
			     {
			      element: uploader,
			      // action: '/path_to/you_server_script.php', 
			      onstart:
			        function(filename)
			        {
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