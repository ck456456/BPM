<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	#bpm_tree_view { width:100%; float:left; margin:0; border:1px solid gray; background:white; overflow:auto; font-family:新細明體;font-size:12px;}
	.jstree-leaf:last-child {height: 50px;}
</style>
<title>表 單 流 程</title>
</head>
<body>
	<div id="TKC_edit">
    <table>
		<tr>
		    <th colspan=4 align=center>表 單 流 程
		    <a id="bpm_tree_f04id" style="display: none;">${ f04id }</a>
		    </th>
		</tr>
		<tr>
			<td colspan=4 >
				<div id="bpm_tree_view"></div>
			</td>
		</tr>
    </table>
	</div>
	<script type="text/javascript" charset="utf-8">
	$("#bpm_tree_view")
	.jstree({
		"core" : { html_titles : true },
		"themes": {"theme": "classic",
					"dots": true,   
					"icons": true}, 
		"plugins" : ["json_data","ui"],
		"json_data" : { 
			"ajax" : {
                url : "json/JBPM_Prop.action?action=treeflow&f04id="+$('#bpm_tree_f04id').text(),
                error : function(){
                },
                success: function(data,status){
                }
            }
		}
	});
	</script>
</body>
</html>