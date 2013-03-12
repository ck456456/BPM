<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css"/>
<style type="text/css">
.demo { width:100%; float:left; margin:0; border:1px solid gray; background:white; overflow:auto; }

</style>

<title>部門人員組織圖</title>
</head>
<body>
	<div id="TKC_edit">
    <table>
		<tr>
		    <th colspan=4 align=center>部 門 人 員 組 織 圖</th>
		</tr>
		<tr>
			<td colspan=4 >
				<div id="demo5" class="demo" style="height:520px;"></div>
			</td>
		</tr>
    </table>
	</div>
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jstree/jquery.jstree.js"></script>
	<script type="text/javascript" src="js/jstree/lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jstree/lib/jquery.hotkeys.js"></script>
	<script type="text/javascript">
	$(function () {
		$("#demo5")
			.jstree({ 
				"plugins" : ["themes","json_data","ui"],
				"json_data" : { 
					"ajax" : {
		                url : "json/JBPMF01.action?action=jstree"
		            }
				}
			});
		});
	</script>
</body>
</html>